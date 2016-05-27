package lesson160519;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import util.Utils;

public class CFExample {

	public static void main(String[] args) {

		ExecutorService defaultExecutor = Executors.newFixedThreadPool(2);

		final ExecutorService daemonsExecutor = 
				Executors.newFixedThreadPool(2,
					new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(true); // prevent the termination of the program
							return thread;
					}
				});

		final ExecutorService noDaemonsExecutor = 
				Executors.newFixedThreadPool(2,
					new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(false); // prevent the termination of the program
							return thread;
					}
				});

//		ExecutorService service = daemonsExecutor; 
//		ExecutorService service = noDaemonsExecutor; 
		ExecutorService service = defaultExecutor; 

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
		System.out.println("futures are thenAccept'ed");

		Utils.delay(3_000);
		System.out.println("3 secs done");

		CompletableFuture.allOf(futures).thenRun(service::shutdown);
		System.out.println("service is shutdown");

	}
}
