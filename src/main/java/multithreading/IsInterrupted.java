package multithreading;

import util.Utils;

public class IsInterrupted {

    static private class Ticker implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is started");
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                if(i % 200_000_000 == 0) {
                    System.out.println(i);
                }
            }
            System.out.println(name + " is interrupted");
        }
    }

    public static void main(String[] args) {

        Thread ticker = new Thread(new Ticker());
        ticker.start();

        Utils.delay(3_000);

        ticker.interrupt();
    }

}
