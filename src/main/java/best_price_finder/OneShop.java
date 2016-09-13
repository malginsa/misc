package best_price_finder;

import java.util.concurrent.Future;

public class OneShop {

	public static void main(String[] args) {

		Shop shop = new Shop("BestShop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorit product");
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Invocation returned after "+ invocationTime + " msec");

		System.out.print("doing some more tasks...");
//		util.Utils.delay(3_000);
		System.out.println("done");

		try {
			Double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price );
		} catch (Exception e) {
			e.printStackTrace();
		}

		long retrivalTime = (System.nanoTime() - start) / 1_000_000 ;
		System.out.println("price returned after " + retrivalTime + " msecs");
	}

}
