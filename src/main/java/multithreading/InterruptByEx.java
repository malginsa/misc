package multithreading;

import util.Utils;

public class InterruptByEx {

    static private class Ticker implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " started");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted by exception");
            }
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Ticker());
        thread.start();

        Utils.delay(2_000);

        thread.interrupt();
    }

}
