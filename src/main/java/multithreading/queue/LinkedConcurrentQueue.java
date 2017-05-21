package multithreading.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedConcurrentQueue implements ConcurrentQueue {

    Queue<Integer> queue;
    ReentrantLock mutex;
    Condition empty;

    public LinkedConcurrentQueue() {
        queue = new LinkedList<>();
        mutex = new ReentrantLock();
        empty = mutex.newCondition();
    }

    @Override
    public void put(Integer item) throws InterruptedException {
        mutex.lock();
        try {
            queue.offer(item);
            System.out.println("put: " + item + toString());
            empty.signal();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public Integer get() throws InterruptedException {
        mutex.lock();
        try {
            while(queue.size() < 1) {
                empty.await();
            }
            Integer item = queue.poll();
            System.out.println("got: " + item + toString());
            return item;
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public String toString() {
        return "  {" +
               "queue=" + queue +
               '}';
    }
}
