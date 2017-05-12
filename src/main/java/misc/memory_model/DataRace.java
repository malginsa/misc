package misc.memory_model;

public class DataRace {

    static class I {int i;}

    public static void main(String[] args) throws InterruptedException {
//        doWork();
        for (int i = 0; i < 10; i++) {
            doWork();
            Thread.sleep(10);
            System.out.println();
        }
    }

    public static void doWork() {

        I a = new I();
        I b = new I();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                I r2 = new I();
                r2.i = a.i;
                b.i = 1;
                System.out.print("  r2.i = " + r2.i);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                I r1 = new I();
                r1.i = b.i;
                a.i = 2;
                System.out.print("  r1.i = " + r1.i);
            }
        });

        thread1.start();
        thread2.start();
    }
}
