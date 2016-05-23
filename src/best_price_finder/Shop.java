package best_price_finder;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import best_price_finder.Discount.Code;

public class Shop {

	private String name;
	private Random random;

	public Shop() {
		this("NoName");
	}

	public Shop(String name) {
		random = new Random();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPrice(String product) {
		double price = calculatePrice(product);
		Code code = Discount.Code.values()[random.nextInt(
			Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", this.name, price, code);
	}

	private double calculatePrice(String product) {
		util.Utils.delay(1000);
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//		new Thread(() -> {
//			try {
//				double price = calculatePrice(product); // invoke synchronous method
//				futurePrice.complete(price);
//			} catch (Exception ex) {
//				futurePrice.completeExceptionally(ex);
//			}
//		}).start();
		futurePrice = CompletableFuture.supplyAsync(() -> calculatePrice(product));
		return futurePrice;
	}

}
