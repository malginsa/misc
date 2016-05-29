package lesson160519;

import java.util.concurrent.CompletableFuture;

import util.Utils;

public class CombineExample {

	public static void main(String[] args) {

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(
			() -> { Utils.delay(1_000); return "hello";});
		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(
			() -> " world!");

		CompletableFuture<String> cf3 = cf1.thenCombine(
			cf2, (s1, s2) -> s1 + s2 );

		cf3.thenAccept(System.out::println).join();
	}
}
