package multithreading;

public class Wait {

    private static Object mutex = new Object();

    private static class MyThread extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("thread " + name + " started");
            synchronized (mutex) {
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("thread " + name + " awaked");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new MyThread().start();
        new MyThread().start();

        Thread.sleep(3_000);

        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

}
