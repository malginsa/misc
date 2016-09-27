package java8_my_samples.lesson160512;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class CompletableFutureExample {

	static class Task implements Callable<String> {
		@Override
		public String call() {
			for (int i = 0; i < "hello".length(); i++) {
				System.out.print("hello".charAt(i));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return "hello";
		}
	}
	
	public static void main(String[] args) {

//		CompletableFuture<String> completableFuture = 
//			CompletableFuture.supplyAsync(task::call);
//		completableFuture.thenAccept(System.out::println);

		ExecutorService service = new ForkJoinPool();
		
		// если service не указан, то он будет создан по-умолчанию в кол-ве ядер процессора 
		CompletableFuture.supplyAsync(new Task()::call, service)
			.thenAccept(System.out::println);
		// .thenAcceptAsync -- в том же потоке
		// а если thenAccept(System.out::println, service2), то в потоке екзекъютора service2 

		for (int i = 0; i < 20; i++) {
			System.out.print("_");
			try {
				Thread.sleep(333);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();

	}
}
