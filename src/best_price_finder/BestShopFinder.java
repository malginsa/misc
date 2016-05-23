package best_price_finder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BestShopFinder {

	private static List<Shop> shops = Arrays.asList(
			new Shop("Shop1"), 
			new Shop("Shop2"), 
			new Shop("Shop3"), 
			new Shop("Shop4"), 
			new Shop("Shop5"), 
			new Shop("Shop61"), 
			new Shop("Shop62"), 
			new Shop("Shop63"), 
			new Shop("Shop64"), 
			new Shop("Shop65"), 
			new Shop("Shop66"), 
			new Shop("Shop7")); 
	


	private static final Executor executor = 
		Executors.newFixedThreadPool(Math.min(shops.size(), 100),
			new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r);
					thread.setDaemon(true); // prevent the termination of the program
					return thread;
			}
		});

	public static List<String> findPrices(String product) {

//		List<CompletableFuture<String>> priceFutures = shops.stream()
//			.map(shop -> CompletableFuture.supplyAsync(
//				() -> shop.getPrice(product), executor))
//			.collect(Collectors.toList());

	List<CompletableFuture<String>> priceFutures = shops.stream()
		.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
		.map(future -> future.thenApply(Quote::parse)) // instantaneously computing => can be done synchronously 

		// thenCompose -- The first and the second CompletableFutures are DEPENDENT 
		.map(future -> future.thenCompose(quote -> 
			CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))

//		.map(future -> future.thenApplyAsync(Discount::applyDiscount, executor))

		.collect(Collectors.toList());

//	priceFutures.stream()
//		.
	
	
	return priceFutures.stream()
		.map(CompletableFuture::join) // join - blocking method 
		.collect(Collectors.toList());

	}

	public static void main(String[] args) {

		long start = System.nanoTime();
		List<String> priceList = findPrices("silence");
		priceList.stream()
			.forEach(s -> System.out.println(s));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in "+ duration +" msecs");
	}
}
