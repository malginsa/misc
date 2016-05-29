package lesson160519;

import java.util.concurrent.CompletableFuture;

import util.Utils;

public class Exeptionally {

	public static void main(String[] args) {
		
		CompletableFuture.supplyAsync(() -> {
				System.out.println("start");
				internalMethon();
				return 0;})
			.exceptionally(t -> {
				System.out.println(t.getMessage());
//				return "need to restart";})
				return 1;}) // return type the same type, as supplyAsync()
			.thenAccept(System.out::println)
			.join();
	}

	private static void internalMethon() {
		Utils.delay(1_000);
		throw new RuntimeException("handmade exeption");
	}
}
