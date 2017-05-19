package multithreading.queue;

interface ConcurrentQueue {

    void put(Integer item) throws InterruptedException;

    Integer get() throws InterruptedException;
}
