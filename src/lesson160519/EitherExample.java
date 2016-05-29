package lesson160519;

import java.util.concurrent.CompletableFuture;

import util.Utils;

public class EitherExample {

	public static void main(String[] args) {
		
		CompletableFuture<String> runner1 = CompletableFuture.supplyAsync(() -> 
			{Utils.randomDelay(1_000, 1_000); return "first runner won";});
		CompletableFuture<String> runner2 = CompletableFuture.supplyAsync(() -> 
			{Utils.randomDelay(1_000, 1_000); return "second runner won";});
		
		runner1.acceptEither(runner2, System.out::println)
			.join();

		System.out.println();
		runner1.thenAcceptBoth(runner2, 
			(s1,s2) -> System.out.println(s1+" and "+s2))
			.join();
		
		System.out.println();
		CompletableFuture.allOf(runner1, runner2)
			.thenAccept(s -> System.out.println("friendship won"))
			.join();
		
		System.out.println();
		runner1.runAfterBoth(runner2, () -> System.out.println("competition is closed"));
		
		// cancel - отменить CF, даже если он уже начался
		
		// complete(res) - отменить CF и определить выполненным с результатом res
		
		// completedFuture(res) - возвращает новый CF, который уже заведомо выполнен
		// с результатом res
		
		// completeExeptionally(ex) - если ещё не выпонен, то определить выполненным
		// с выбросом исключения ex
		
		// getNow 
		
	}
}
