package misc;

import util.Utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {

    private final Semaphore bottom;
    private final Semaphore top;

    private Queue<Integer> queue;
    private Object mutex;
    private Lock lock;

    public MyBlockingQueue(int size) {
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

    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(3);
        int numberOfElements = 100;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numberOfElements; i++) {
                    int finalI = i;
                    Utils.randomDelay(50);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                myBlockingQueue.put(finalI);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }).start();

        for (int i = 0; i < numberOfElements; i++) {
            Utils.randomDelay(50);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        myBlockingQueue.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
