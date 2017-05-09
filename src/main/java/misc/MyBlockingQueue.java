package misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MyBlockingQueue {

    private final Semaphore semaphore;

    private Queue<Integer> queue;

    public MyBlockingQueue(int size) {
        semaphore = new Semaphore(size);
        queue = new LinkedList<>();
    }

    public Integer get() {
        semaphore.release();
        return queue.poll();
    }

    public void add(Integer element) throws InterruptedException {
        semaphore.acquire();
        queue.offer(element);
    }

    @Override
    public String toString() {
        return "MyBlockingQueue{" +
                "semaphore=" + semaphore +
                ", queue=" + queue +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(3);
        System.out.println(myBlockingQueue);
        myBlockingQueue.add(1);
        System.out.println(myBlockingQueue);
        myBlockingQueue.add(2);
        System.out.println(myBlockingQueue);
        myBlockingQueue.add(3);
        System.out.println(myBlockingQueue);
        myBlockingQueue.add(4);
        System.out.println(myBlockingQueue);
        System.out.println("got = " + myBlockingQueue.get());
        System.out.println(myBlockingQueue);
    }
}
