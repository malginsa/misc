package java8_my_samples.lesson160505;

public class BasicMultiThread {

	public static class MyRunnable implements Runnable {
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("inside MyRunnable");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread[] threads = new Thread[10];

		for (int i = 0; i < 10; i++) {
			 threads[i] = new Thread(new MyRunnable());
		}
		
		for (int i = 0; i < 10; i++) {
			 threads[i].start();
		}
		
//		threads[i].join();
		
	}
}
