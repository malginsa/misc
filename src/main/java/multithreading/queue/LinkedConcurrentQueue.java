package multithreading.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedConcurrentQueue implements ConcurrentQueue {

    Queue<Integer> queue;
    ReentrantLock mutex;

    public LinkedConcurrentQueue() {
        queue = new LinkedList<>();
        mutex.lock();
    }

    @Override
    public void put(Integer item) throws InterruptedException {
        mutex.lock();
        try {
            queue.offer(item);
            System.out.println("put: " + item + toString());
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public Integer get() throws InterruptedException {

        return null;
        //        mutex.lock();
//        try {
//            if (queue.size() < 1) {
//                mutex.
//            }
//        } finally {
//            mutex.unlock();
//        }
    }

    @Override
    public String toString() {
        return "  {" +
               "queue=" + queue +
               ", mutex=" + mutex +
               '}';
    }
}
