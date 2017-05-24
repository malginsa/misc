package multithreading;

public class SimulateCompareAndSwap {

    private int value;
    private int fake;

    public synchronized int get() {
        return value;
    }

    public synchronized int getFake() {
        return fake;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        } else {
            fake++;
        }
        return oldValue;
    }

}
