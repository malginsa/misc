package lesson160505;

public class BasicThread {

	public static class SlowRunnable implements Runnable {
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("inside SlowRunnable");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
//		Thread thread = new Thread(new SlowRunnable());
		Thread thread = new Thread(() -> System.out.println("inside Runnable"));
		thread.start();
		thread.join(); // ? can be omitted
	
	}
}
