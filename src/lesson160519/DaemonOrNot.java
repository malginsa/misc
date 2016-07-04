package lesson160519;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import util.Utils;

public class DaemonOrNot {

	public static void main(String[] args) {
		
		// by default, threads from newFixedThreadPool() are NOT daemons
		ExecutorService fixedPollExecutor = Executors.newFixedThreadPool(1);
		// by default, threads from newCachedThreadPool() are NOT daemons
		ExecutorService cachedPoolExecutor = Executors.newCachedThreadPool();

		// default thread pool are daemons
		
		// по умолчанию, все методы, оканчивающиеся на ..Async(consumer)
		// используют стандартный Fork-join poll, в котором
		// каждая задача выполняется в новом thead'е 

		final ExecutorService daemonsExecutor = 
				Executors.newFixedThreadPool(2,
					new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(true); // prevent the termination of the Main
							return thread;
					}
				});

		final ExecutorService noDaemonsExecutor = 
				Executors.newFixedThreadPool(2,
					new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(false);
							return thread;
					}
				});

		ExecutorService service = daemonsExecutor; 
//		ExecutorService service = noDaemonsExecutor; 
//		ExecutorService service = fixedPollExecutor; 
//		ExecutorService service = cachedPoolExecutor; 

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> 
			{Utils.delay(3000); return "future finished normally";}, service);
		System.out.println("future are defined");

		future.thenAccept(System.out::println);
		System.out.println("futures are accepted");

		Utils.delay(2_000);
		System.out.println("2 secs done");

		CompletableFuture.allOf(future).thenRun(service::shutdown);
		System.out.println("service is shutdown");

	}
}
