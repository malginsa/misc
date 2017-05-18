package multithreading;

import util.Utils;

public class Join {

    public static void main(String[] args) throws InterruptedException {

        Thread ticker = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("tick "+ i);
                    Utils.delay(1_000);
                }
            }
        });
        ticker.start();

        ticker.join(); // current ticker (main) blocked until ticker finished

        System.out.println("main finished");
    }
}
