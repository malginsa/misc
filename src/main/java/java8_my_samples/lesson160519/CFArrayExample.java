package java8_my_samples.lesson160519;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Utils;

public class CFArrayExample {

	public static void main(String[] args) {

		// by default, threads from newFixedThreadPool() are NOT daemons
		ExecutorService service = Executors.newFixedThreadPool(2);

		CompletableFuture[] futures = new CompletableFuture[6];

		for (int i = 0; i < 6; i++) {
			final int j = i;
			futures[i] = CompletableFuture.supplyAsync(() -> 
				{Utils.delay(2000); return "hello " + j;}, service);
		}

		System.out.println("futures are defined");

		for (int i = 0; i < futures.length; i++) {
			futures[i].thenAccept(System.out::println);
		}
		System.out.println("futures are accepted");

		Utils.delay(3_000);
		System.out.println("3 secs done");

		CompletableFuture.allOf(futures).thenRun(service::shutdown);
		System.out.println("service is shutdown");

	}
}
