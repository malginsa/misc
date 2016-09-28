package java8_my_samples.lesson160518;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import util.Utils;

public class CompletableFutureExample2 {

	static class DataProvider {
		
		private double getData() {
			Utils.delay(3000);
			return Math.random();
		}
		
		public Future<Double> getDataAsync() {
			CompletableFuture<Double> futureData = new CompletableFuture<>();
			// effectively final
			// futureData инициализируется только раз
			new Thread(new Runnable() {
				@Override
				public void run() {
					double result = getData();
					futureData.complete(result);
				}
			}).start();
			return futureData;
		}
	}

	public static void main(String[] args) {

		DataProvider data = new DataProvider();
		System.out.println("getting data");
//		try {
//			Double d = data.getDataAsync().get();
//			System.out.println(d);
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}

		CompletableFuture.supplyAsync(data::getData).thenAccept(System.out::println);
		// println() будет выполнен в том же потоке, что и getData()
		// если thenAcceptAsync, то в потоке общего пула к кол-вом тредов = кол процессоров
		// но можно и явно указать свой executor 
	}
}
