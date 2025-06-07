package org.javacommunity.concurrency;

import java.util.concurrent.*;

public class ThreadUsingCallable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future1;
        Future<Integer> future2;
        System.out.println("Starting");
        future1 = executorService.submit(() -> { // implement Callable interface using Lambda
            System.out.println(" Entering thread -- "+Thread.currentThread().getName());
            int sum = 0;
            for (int i = 1; i < 10; i++) {
                sum += i;
            }
            System.out.println(" Exiting thread -- "+Thread.currentThread().getName());
            return sum;
        });
        future2 = executorService.submit(() -> { // implement Callable interface using Lambda
            System.out.println(" Entering thread -- "+Thread.currentThread().getName());
            int product = 1;
            for (int i = 1; i < 10; i++) {
                product *= i;
            }
            System.out.println(" Exiting thread -- "+Thread.currentThread().getName());
            return product;
        });
        try {
            Thread.sleep(5000l); // sleep for 5 seconds, let threads complete their executions
            System.out.println("Getting result of threads returned by Callable interface");
            System.out.println("Result of Future1 = "+future1.get());
            System.out.println("Result of Future2 = "+future2.get());
        } catch (InterruptedException | ExecutionException exc) {
            System.out.println(exc);
        }
        executorService.shutdown();
        System.out.println("Done");
    }
}