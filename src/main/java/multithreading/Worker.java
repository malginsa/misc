package multithreading;

import util.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class Worker {

    private class Runner implements Runnable {

        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }

    private Queue<Runnable> tasks = new LinkedList<>();
    private Thread t = new Thread(new Runner());

    public Worker() {
        t.start();
    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            tasks.offer(task);
            tasks.notify();
        }
    }

    public static void main(String[] args) {

        Worker worker = new Worker();

        Utils.delay(3_000);

        worker.execute(() -> {
            System.out.println("The first task");
        });

    }

}
