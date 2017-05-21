package multithreading.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueReentrantLock implements ConcurrentQueue {

    private Queue<Integer> queue;
    private ReentrantLock mutex;
    private Condition isFull;
    private Condition isEmpty;
    private int capacity;

    public BlockingQueueReentrantLock(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
        mutex = new ReentrantLock();
        isEmpty = mutex.newCondition();
        isFull = mutex.newCondition();
    }

    @Override
    public void put(Integer item) throws InterruptedException {
        mutex.lock();
        try {
            while(queue.size() >= capacity) {
                isFull.awaitUninterruptibly();
            }
            queue.offer(item);
            System.out.println("put " + item + toString());
            isEmpty.signal();
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public Integer get() throws InterruptedException {
        Integer item;
        mutex.lock();
        try {
            while(queue.isEmpty()) {
                isEmpty.await();
            }
            item = queue.poll();
            System.out.println("got " + item + toString());
            isFull.signal();
        } finally {
            mutex.unlock();
        }
        return item;
    }

    @Override
    public String toString() {
        return "  {" +
               "queue=" + queue +
               '}';
    }
}
