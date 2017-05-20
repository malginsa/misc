package multithreading;

import util.Utils;

public class IsInterrupted {

    static private class Ticker extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is started");
            int i = 0;
            while (!this.isInterrupted()) {
                i++;
                if(i % 200_000_000 == 0) {
                    System.out.println(i);
                }
            }
            System.out.println(name + " is interrupted");
        }
    }

    public static void main(String[] args) {

        Ticker ticker = new Ticker();
        ticker.start();

        Utils.delay(5_000);

        ticker.interrupt();
    }

}
