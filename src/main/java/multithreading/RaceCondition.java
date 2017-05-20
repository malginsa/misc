package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {

    private static int count = 0;
    private static AtomicInteger atomicCount;
    private static int AMOUNT = 1_000_000;

    private static class BuggyCounter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < AMOUNT; i++) {
                count++;
            }
        }
    }

    private static class CorrectCounter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < AMOUNT; i++) {
                atomicCount.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable counter = new BuggyCounter();
        fireCounter(counter);
        System.out.println("Using BuggyCounter = " + count);
        atomicCount = new AtomicInteger();
        counter = new CorrectCounter();
        fireCounter(counter);
        System.out.println("Using CorrectCounter = " + atomicCount.get());
    }

    private static void fireCounter(Runnable counter) throws InterruptedException {
        Thread thread1 = new Thread(counter);
        Thread thread2 = new Thread(counter);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
