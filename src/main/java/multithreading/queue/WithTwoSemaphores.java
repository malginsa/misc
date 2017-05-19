package multithreading.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WithTwoSemaphores implements MyBlockingQueue {

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

