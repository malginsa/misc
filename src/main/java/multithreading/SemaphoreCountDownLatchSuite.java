package multithreading;

import util.Utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreCountDownLatchSuite {

    private static class SemaphoreCountDownLatch {

        private int capacity;
        private Semaphore sem;
        private AtomicInteger counter;

        public SemaphoreCountDownLatch(int capacity) {
            this.capacity = capacity;
            sem = new Semaphore(0);
            counter = new AtomicInteger(0);
        }

        public void awaits() throws InterruptedException {
            sem.acquire();
        }

        public void countDown() {
            int value = counter.incrementAndGet();
            if (value == 3) {
                sem.release(capacity);
            }
        }
    }

    private static class Runner implements Runnable {

        private SemaphoreCountDownLatch latch;

        public Runner(SemaphoreCountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Runner " + name + " awaits");
            try {
                latch.awaits();
                System.out.println("Runner " + name + " runs");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int RUNNERS_COUNT = 4;

        SemaphoreCountDownLatch latch = new SemaphoreCountDownLatch(RUNNERS_COUNT);

        for (int i = 0; i < RUNNERS_COUNT; i++) {
            new Thread(new Runner(latch)).start();
        }

        Utils.delay(1_000);
        System.out.println("Ready...");
        latch.countDown();
        Utils.delay(1_000);
        System.out.println("Steady...");
        latch.countDown();
        Utils.delay(1_000);
        System.out.println("Go!");
        latch.countDown();
    }

}
