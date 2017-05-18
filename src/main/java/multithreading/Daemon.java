package multithreading;

import util.Utils;

public class Daemon {

    private static class Ticker implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i < Integer.MAX_VALUE) {
                System.out.println("tick " + i++);
                Utils.delay(1_000);
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Ticker());
        thread.setDaemon(true);
        thread.start();
        Utils.delay(10_000);
    }

}
