package multithreading.queue;

import util.Utils;

/**
 * Collections of realizations BlockingQueue:
 * TwoSemaphores,
 * WithWaitNotify
 */

public class LinkedBlockingQueue {

    private final static int QUEUE_SIZE = 3;
    private final static int NUMBER_OF_ITEMS_IN_QUEUE = 100;
    private final static int DELAY_BETWEEN_OPERATIONS = 50; // in milli seconds

    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue blockingQueue = new WithWaitNotify(QUEUE_SIZE);

        fireTest(blockingQueue);

    }

    private static void fireTest(MyBlockingQueue blockingQueue) {
        new Thread(() -> {
            for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
                int finalI = i;
                Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
                Thread thread = new Thread(() -> {
                    try {
                        blockingQueue.put(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread.setName(" : put " + i + " : ");
                thread.start();
            }
        }).start();

        for (int i = 0; i < NUMBER_OF_ITEMS_IN_QUEUE; i++) {
            Utils.randomDelay(DELAY_BETWEEN_OPERATIONS);
            Thread thread = new Thread(() -> {
                try {
                    blockingQueue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName(" : get " + i + " : ");
            thread.start();
        }
    }
}
