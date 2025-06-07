package org.javacommunity.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor  = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(() -> {
            Thread.sleep(1000);
            return 100000000 * 100000000;});
		
		 System.out.println("Non Blocking Calculion...");
		 
		while(!future.isDone()) {
		    System.out.println("Calculating...");
		    try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		 
		try {
			Integer result = future.get();
			System.out.println("result = "+result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }
}
