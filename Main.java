import java.util.concurrent.*;
import java.util.*;

class BankAccount {
    @SuppressWarnings("unused")
    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // synchronized method (Critical Section)
    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() +
                " Deposited: " + amount + " | Balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +
                    " Withdrawn: " + amount + " | Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " Failed Withdrawal (Insufficient Balance)");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}

class Transaction implements Callable<String> {
    private BankAccount account;
    private String type;
    private double amount;

    public Transaction(BankAccount account, String type, double amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(500); // simulate delay

            if (type.equals("deposit")) {
                account.deposit(amount);
            } else if (type.equals("withdraw")) {
                account.withdraw(amount);
            }

            return "Transaction Completed: " + type + " " + amount;

        } catch (InterruptedException e) {
            return "Transaction Failed";
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        BankAccount account = new BankAccount(101, 1000);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                new Transaction(account, "deposit", 500),
                new Transaction(account, "withdraw", 700),
                new Transaction(account, "withdraw", 1000),
                new Transaction(account, "deposit", 300)
        );

        System.out.println("Processing Transactions...\n");

        // Using Future
        List<Future<String>> results = executor.invokeAll(tasks);

        for (Future<String> f : results) {
            System.out.println(f.get());
        }

        // Using CompletableFuture (Async)
        CompletableFuture<Void> asyncTask =
                CompletableFuture.runAsync(() -> {
                    account.deposit(200);
                    account.withdraw(150);
                });

        asyncTask.get();

        System.out.println("\nFinal Balance: " + account.getBalance());

        executor.shutdown();
    }
}