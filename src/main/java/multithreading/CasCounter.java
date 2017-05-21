package multithreading;

public class CasCounter {

    private SimulateCompareAndSwap atomic;

    public CasCounter() {
        atomic = new SimulateCompareAndSwap();
    }

    public int get() {
        return atomic.get();
    }

    public int increment() {
        int v;
        do {
            v = atomic.get();
        } while (atomic.compareAndSwap(v, v+1) != v);
        return v+1;
    }

    public static void main(String[] args) {
        CasCounter casCounter = new CasCounter();

        class Task implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    casCounter.increment();
                }
            }
        }

//        new Thread(new Task()).start();

        for (int i = 0; i < 1_000_000; i++) {
            casCounter.increment();
        }

        System.out.println(casCounter.get());
    }
}
