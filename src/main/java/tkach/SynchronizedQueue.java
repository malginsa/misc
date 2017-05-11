package tkach;

import java.util.concurrent.Semaphore;

public class SynchronizedQueue {

    private Integer baton;
    private Semaphore semaphore;

    public SynchronizedQueue() {
        semaphore = new Semaphore(1);
    }

    private void put(Integer item) {

    }

    private Integer get() {

    }

    public static void main(String[] args) {

    }
}
