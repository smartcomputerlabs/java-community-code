package org.javacommunity.concurrency;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

class MyCallable implements Callable<String> {
	public String call() throws Exception {
		Thread.sleep(2000);
		return "DONE";
	}
}

public class ShutdownNowDemo {

	public static <OptionalDouble> void main(String[] args) throws Exception {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<String> future = es.submit(new MyCallable());
		System.out.println(future.get()); // 1
		// es.shutdownNow(); //2
		es.shutdown(); // 2

		Runnable r = () -> System.out.println("In Runnable");
		Callable<Integer> c = () -> {
			System.out.println("In Callable");
			return 0;
		};
		ExecutorService es1 = Executors.newCachedThreadPool();

		// INSERT CODE HERE

		Future<Integer> fi1 = es1.submit(c);
		Future fi2 = es1.submit(r);
		System.out.println(fi2.get());
		es.shutdown();

		Path p1 = Paths.get("c:\\..\\temp\\test.txt");
		System.out.println(p1.normalize().toUri());

		IntStream is1 = IntStream.of(1, 3, 5); // 1
		OptionalDouble x = (OptionalDouble) is1.filter(i -> i % 2 == 0).average(); // 2
		System.out.println(x); // 3
		//Stream.iterate(0, (n) -> n + 1).limit(2).average();
	}

}

interface Runner {
	public void run();
}
