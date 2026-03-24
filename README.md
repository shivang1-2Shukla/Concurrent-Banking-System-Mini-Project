
#💻 Mini Project: Concurrent Banking System in Java
📌 Project Overview

The Concurrent Banking System is a Java-based mini project that demonstrates how multiple banking transactions can be processed simultaneously using multithreading and concurrency utilities. The system simulates real-world banking operations such as deposits and withdrawals on a shared bank account while ensuring data consistency and thread safety.

🎯 Objectives
To understand multithreading in Java
To implement thread-safe operations using synchronized
To use ExecutorService, Callable, Future, and CompletableFuture
To simulate real-time concurrent transactions
⚙️ Technologies Used
Java (JDK 8+ / Java 19 compatible)
Java Concurrency API:
ExecutorService
Callable
Future
CompletableFuture
🧩 System Components
1. BankAccount Class
Represents a bank account with:
Account number
Balance
Provides synchronized methods:
deposit() → Adds money safely
withdraw() → Deducts money with balance check
getBalance() → Returns current balance
Ensures mutual exclusion (critical section handling)
2. Transaction Class
Implements Callable<String>
Represents a single transaction:
Deposit or Withdrawal
Simulates delay using Thread.sleep()
Returns transaction status
3. Main Class
Creates a shared bank account
Uses ExecutorService with a thread pool
Executes multiple transactions concurrently
Collects results using Future
Demonstrates asynchronous execution using CompletableFuture
Displays final account balance
🔄 Working of the System
Multiple transactions are created (deposit & withdraw)
These tasks are submitted to a thread pool
Threads execute transactions concurrently
synchronized methods prevent data inconsistency
Results are collected and displayed
Additional async operations are performed using CompletableFuture
Final balance is printed
🔐 Concurrency Control
Uses synchronized methods to avoid:
Race conditions
Inconsistent balance updates
Ensures only one thread accesses critical section at a time
✨ Key Features
Thread-safe banking operations
Parallel transaction processing
Use of modern concurrency tools
Simulation of real-world banking scenarios
Combination of synchronous and asynchronous execution
📊 Sample Output Behavior
Threads perform deposits and withdrawals
Some withdrawals may fail due to insufficient balance
Final balance reflects all valid transactions
🚀 Conclusion

This project effectively demonstrates how Java concurrency mechanisms can be applied to build a safe and efficient banking system. It highlights the importance of synchronization and modern asynchronous programming techniques in handling multiple operations simultaneously.
