package multithreading;

public class RaceCondition {

    private static int count = 0;
    private static int AMOUNT = 1_000_000;

    private static class Counter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < AMOUNT; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread thread1 = new Thread(counter);
        Thread thread2 = new Thread(counter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count = " + count);
    }
}
