package best_price_finder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import best_price_finder.ExchangeService.Money;

public class BestShopFinder {

	private static List<Shop> shops = Arrays.asList(
			new Shop("Shop1"), 
			new Shop("Shop2"), 
			new Shop("Shop3"), 
			new Shop("Shop4"), 
			new Shop("Shop5"), 
			new Shop("Shop6"), 
			new Shop("Shop7"), 
			new Shop("Shop8"), 
			new Shop("Shop9")); 
	
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

		List<CompletableFuture<String>> priceFutures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscount(product), executor))
			.map(future -> future.thenApply(Quote::parse)) // instantaneously computing => can be done synchronously 
			// thenCompose -- The first and the second CompletableFutures are DEPENDENT 
			.map(future -> future.thenCompose(quote -> 
				CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
			// the same
			// .map(future -> future.thenApplyAsync(Discount::applyDiscount, executor))
			.collect(Collectors.toList());
	
		return priceFutures.stream()
			.map(CompletableFuture::join) // join() - blocking method 
			.collect(Collectors.toList());
	}

	public static List<String> findPricesInUSD(String product) {
		
        List<CompletableFuture<Double>> priceFutures = new ArrayList<>();

		for (Shop shop : shops) {
            CompletableFuture<Double> futurePriceInUSD = 
            	CompletableFuture.supplyAsync(
            		() -> shop.getPrice(product), executor)
            	.thenCombine( 
// 				thenCombine -- The first and the second CompletableFutures are INDEPENDENT
//				thenCombine without "Async", because BiFunction can be calculated instantaneously
            		CompletableFuture.supplyAsync(
            			() -> ExchangeService.getRate(Money.EUR, Money.USD)),
            		(price, rate) -> price * rate);
			priceFutures.add(futurePriceInUSD);
		}

		List<String> prices = priceFutures.stream()
			.map(CompletableFuture::join)
			.map(price -> /* shop.getName() + */ " price is " + price)
			.collect(Collectors.toList());

		return prices;
	}

	public static Stream<CompletableFuture<String>> findPricesStream (String product) {
		return shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
				() -> shop.getPriceWithDiscount(product), executor))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
				() -> Discount.applyDiscount(quote), executor)));
	}

	public static void main(String[] args) {

		long start = System.nanoTime();

//		List<String> priceList = findPricesInUSD("silence");
//		priceList.stream().forEach(s -> System.out.println(s));

		CompletableFuture[] futures = findPricesStream("silence")
			.map(f -> f.thenAccept(s -> System.out.println(
				s + " (done in " + ((System.nanoTime()-start) / 1_000_000) + " msecs)")))
			.toArray(size -> new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join(); // all the shops
		// CompletableFuture.anyOf(futures).join(); // only the fastest shop
		
		// the same result
//		List<CompletableFuture<Void>> futures = findPricesStream("silence")
//			.map(future -> future.thenAccept(System.out::println))
//			.collect(Collectors.toList());
//		futures.stream()
//			.map(CompletableFuture::join)
//			.collect(Collectors.toList());

		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in "+ duration +" msecs");
	}
}
