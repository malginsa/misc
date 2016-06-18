package misc;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import util.Utils;

public class SemaphoreTest {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Integer[] even = IntStream
			.rangeClosed(10, 19)
			.boxed()
			.toArray(size -> new Integer[size]);

		Integer[] odd = IntStream
			.rangeClosed(20, 29)
			.boxed()
			.toArray(size -> new Integer[size]);

		Arrays.stream(even).forEachOrdered(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(odd).forEachOrdered(i -> System.out.print(i + " "));
		System.out.println();

		Semaphore semaphoreSpilling = new Semaphore(0);

		executor.execute(() -> {
			for (int i = 0; i < even.length; i++) {
				Utils.randomDelay(100, 500);
				System.out.print(even[i] + " out  ");
				even[i] = null;
				semaphoreSpilling.release();
				System.out.println(semaphoreSpilling.availablePermits());
			}
		});

		for (int i = 0; i < even.length; i++) {
			Utils.randomDelay(100, 1000);
			semaphoreSpilling.acquireUninterruptibly();
			System.out.println(even[i] + " replaced with " + odd[i] + "  " + semaphoreSpilling.availablePermits());
			even[i] = odd[i];
		}

		Arrays.stream(even).forEachOrdered(i -> System.out.print(i + " "));
		System.out.println();

		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
