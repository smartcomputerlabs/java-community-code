package org.javacommunity.concurrency;

public class ThreadUsingRunnable {
    public static void main(String[] args) {
       new Thread(() -> {   // lambda expression to implement thread using Runnable interface
           System.out.println("Thread Using Runnable and Lambda Example 1");
       }).start();

        // OR

        Runnable runnable = () -> { // lambda expression to implement thread using Runnable interface
            System.out.println("Thread Using Runnable and Lambda Example 1");
        };
        new Thread(runnable).start();
    }
}
