package tkach;

import util.Utils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronousQueue {

    private static int runnersCount = 7;
    private Integer baton;
    private ReentrantLock lock;
    private AtomicBoolean batonIsFree;
    private Object mutex;

    public SynchronousQueue() {
        mutex = new Object();
        lock = new ReentrantLock();
        batonIsFree = new AtomicBoolean(false);
    }

    private void put(Integer item) throws InterruptedException {
        synchronized (mutex) {
            boolean iAmHolder = batonIsFree.compareAndSet(false, true);
            baton = item;
            if (iAmHolder) {
                mutex.wait();
                batonIsFree.set(false);
            } else {
                mutex.notify();
            }
        }
    }

    private Integer get() throws InterruptedException {
        synchronized (mutex) {
            boolean iAmHolder = batonIsFree.compareAndSet(false, true);
            if (iAmHolder) {
                mutex.wait();
                batonIsFree.set(false);
            } else {
                mutex.notify();
            }
            return baton;
        }
    }

    public static void main(String[] args) {

        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < runnersCount; i++) {
                    Utils.randomDelay(100);
                    System.out.println("is going to put: " + i);
                    try {
                        synchronousQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < runnersCount; i++) {
                    Utils.randomDelay(100);
                    try {
                        System.out.println("got: " + synchronousQueue.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
