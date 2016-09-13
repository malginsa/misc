package lesson160519;

import java.util.concurrent.CompletableFuture;

import util.Utils;

public class ApplyVsCompose {

	public static void main(String[] args) {

		CompletableFuture.supplyAsync(() -> {Utils.delay(1_000);; return "hello";})
			.thenApply(String::toUpperCase)
			.thenAccept(System.out::println)
			.join();


		// Compose делает "flat"
		CompletableFuture.supplyAsync(() -> {Utils.delay(1_000);; return "hello";})
			.thenCompose(s -> CompletableFuture.supplyAsync(s::toUpperCase))
			.thenAccept(System.out::println)
			.join();
	}
}
