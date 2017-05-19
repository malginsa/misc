package multithreading.queue;

import java.util.LinkedList;
import java.util.Queue;

class BlockingWithWaitNotify implements ConcurrentQueue {

    private final Object mutex;
    private Queue<Integer> queue;
    private int capacity;

    public BlockingWithWaitNotify(int capacity) {
        this.capacity = capacity;
        mutex = new Object();
        queue = new LinkedList<>();
    }

    @Override
    public void put(Integer item) throws InterruptedException {
        synchronized (mutex) {
            while (queue.size() >= capacity) {
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
            while (queue.isEmpty()) {
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
