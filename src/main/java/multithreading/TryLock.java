package multithreading;

import util.Utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        class Task implements Runnable {

            @Override
            public void run() {
                System.out.println("Task started");
                try {
                    boolean success = lock.tryLock(3, TimeUnit.SECONDS);
                    if (!success) {
                        System.out.println("timeout");
                        return;
                    }
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    return;
                }
                try {
                    System.out.println("got lock");
                } finally {
                    lock.unlock();
                    System.out.println("unlocked");
                }
            }
        }

        lock.lock();
        Thread thread = new Thread(new Task());
        thread.start();
        Utils.delay(1_000);
        thread.interrupt();
    }

}
