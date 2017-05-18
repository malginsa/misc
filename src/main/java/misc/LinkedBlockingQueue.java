package misc;

import util.Utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Collections of realizations BlockingQueue:
 * TwoSemaphores,
 * WithWaitNotify
 */

public class LinkedBlockingQueue {

    private final static int QUEUE_SIZE = 3;
    private final static int NUMBER_OF_ITEMS_IN_QUEUE = 100;
    private final static int DELAY_BETWEEN_OPERATIONS = 50; // in milli seconds

    interface MyBlockingQueue {

        void put(Integer item) throws InterruptedException;

        Integer get() throws InterruptedException;
    }

    static class WithWaitNotify implements MyBlockingQueue {

        private final Object mutex;
        private Queue<Integer> queue;
        private int capacity;

        public WithWaitNotify(int capacity) {
            this.capacity = capacity;
            mutex = new Object();
            queue = new LinkedList<>();
        }

        @Override
        public void put(Integer item) throws InterruptedException {
            synchronized (mutex) {
                if (queue.size() >= capacity) {
                    mutex.wait();
                }
                queue.offer(item);
                mutex.notify();
                System.out.println("put: " + item + toString());
            }
        }

        @Override
        public Integer get() throws InterruptedException {
            Integer item;
            synchronized (mutex) {
                if (queue.isEmpty()) {
                    mutex.wait();
                }
                item = queue.poll();
                mutex.notify();
                System.out.println("got: " + item + toString());
            }
            return item;
        }

        @Override
        public String toString() {
            return "  queue={" + queue +
                   '}';
        }
    }

    static class WithTwoSemaphores implements MyBlockingQueue {

        private final Semaphore bottom;
        private final Semaphore top;
        private final Object mutex;
        private final Lock lock;
        private Queue<Integer> queue;

        public WithTwoSemaphores(int capacity) {
            bottom = new Semaphore(0, true);
            top = new Semaphore(capacity, true);
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

        MyBlockingQueue blockingQueue = new WithWaitNotify(QUEUE_SIZE);

        new Thread(() -> {
            for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
                int finalI = i;
                Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
                Thread thread = new Thread(() -> {
                    try {
                        blockingQueue.put(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread.setName(" : put " + i + " : ");
                thread.start();
            }
        }).start();

        for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
            Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
            Thread thread = new Thread(() -> {
                try {
                    blockingQueue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName(" : get " + i + " : ");
            thread.start();
        }
    }
}
