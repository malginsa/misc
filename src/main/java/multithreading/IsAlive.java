package multithreading;

import util.Utils;

public class IsAlive {


    static private class Ticker implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " started");
            Utils.delay(3_000);
            System.out.println(name + " finished");
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Ticker());
        thread.start();

        Utils.delay(1_000);

        System.out.println("Alive status of " + thread.getName() + " is " + thread.isAlive()); // true

        Utils.delay(5_000);

        System.out.println("Alive status of " + thread.getName() + " is " + thread.isAlive()); // false
    }

}

