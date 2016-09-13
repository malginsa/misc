package lesson160519;

import java.util.concurrent.CompletableFuture;

import util.Utils;

public class HandleExample {

	public static void main(String[] args) {
		
		CompletableFuture.supplyAsync(() -> {
				System.out.println("start");
				internalMethon();
				return 0;})
			.handle((res, t) -> {
				if(null != t) {
					System.out.println(t.getMessage());
					return 1;
				}
				return 0;
			})
//			.thenAccept(System.out::println)
			.join();
	}

	private static void internalMethon() {
		Utils.delay(1_000);
		throw new RuntimeException("artificial exeption");
	}

}
