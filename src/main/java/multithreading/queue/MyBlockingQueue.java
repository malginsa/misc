package multithreading.queue;

interface MyBlockingQueue {

    void put(Integer item) throws InterruptedException;

    Integer get() throws InterruptedException;
}
