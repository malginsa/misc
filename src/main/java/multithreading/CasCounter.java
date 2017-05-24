package multithreading;

public class CasCounter {

    private SimulateCompareAndSwap atomic;

    public CasCounter() {
        atomic = new SimulateCompareAndSwap();
    }

    public int get() {
        return atomic.get();
    }
    public int getFake() {
        return atomic.getFake();
    }

    public int increment() {
        int v;
        do {
            v = atomic.get();
        } while (v != atomic.compareAndSwap(v, v+1));
        return v+1;
    }

    public static void main(String[] args) throws InterruptedException {
        CasCounter casCounter = new CasCounter();

        class Task implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    casCounter.increment();
                }
            }
        }

        Thread thread = new Thread(new Task());
        thread.start();

        for (int i = 0; i < 1_000_000; i++) {
            casCounter.increment();
        }

        thread.join();
        System.out.println(casCounter.get());

        System.out.println("counter of fakes = " + casCounter.getFake());
    }
}
