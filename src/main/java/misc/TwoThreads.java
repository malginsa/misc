package misc;

public class TwoThreads {

    static class MyThread extends Thread {
        int count;
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 1000; i++) {
                System.out.println(name + "  " +(count++));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();

        thread1.join();
        thread1.join();
    }
}
