package misc;

import util.Utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Collections of realizations:
 * TwoSemaphores,
 * ...
 */

public class LinkedBlockingQueue {

    private final static int QUEUE_SIZE = 3;
    private final static int NUMBER_OF_ITEMS_IN_QUEUE = 100;
    private final static int DELAY_BETWEEN_OPERATIONS = 50;

    static class TwoSemaphores {

        private final Semaphore bottom;
        private final Semaphore top;
        private final Object mutex;
        private final Lock lock;
        private Queue<Integer> queue;

        public TwoSemaphores(int size) {
            bottom = new Semaphore(0, true);
            top = new Semaphore(size, true);
            queue = new LinkedList<>();
            mutex = new Object();
            lock = new ReentrantLock(true);
        }

        public Integer get() throws InterruptedException {
            Integer result;
            bottom.acquire();
            lock.lock();
            try {
                result = queue.poll();
                top.release();
                System.out.println("got: " + result + toString());
            } finally {
                lock.unlock();
            }
            return result;
        }

        public void put(Integer element) throws InterruptedException {
            top.acquire();
            lock.lock();
            try {
                queue.offer(element);
                bottom.release(); // take over critical section
                System.out.println("put: " + element + toString());
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "   {" +
                   "bottom=" + bottom.availablePermits() +
                   " top=" + top.availablePermits() +
                   " queue=" + queue +
                   "} ";
        }
    }


    public static void main(String[] args) throws InterruptedException {

        TwoSemaphores blockingQueue = new TwoSemaphores(QUEUE_SIZE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
                    int finalI = i;
                    Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                blockingQueue.put(finalI);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }).start();

        for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
            Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        blockingQueue.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
