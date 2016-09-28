package java8_my_samples.lesson160512;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Futures {

	 // пример задачи не поддающейся дроблению
	
	static class Task implements Callable<String> {
		@Override
		public String call() throws InterruptedException {
			for (int i = 0; i < "hello".length(); i++) {
				System.out.print("hello".charAt(i));
				Thread.sleep(1000);
			}
			return "hello";
		}
	}
	
	public static void main(String[] args) {
	
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		Future<String> future = service.submit(new Task());
		
		for (int i = 0; i < 20; i++) {
			System.out.print("_");
			try {
				Thread.sleep(333);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		
		try {
			String result = future.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		service.shutdown();
		
	}
}
