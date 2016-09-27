package patterns.singleton.softbinding;

public class ThreadSafeSingleton implements STService {

    // It is thread-safe, because creation of an instance
    // and creation field INSTANCE will placed in the same thread.
    private static ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton get () {
        return INSTANCE;
    }

    public static void main(String[] args) {

        final ThreadSafeSingleton threadSafeSingleton = ThreadSafeSingleton.get();
    }

    @Override
    public void doSomething() {
        System.out.println("I'm a thread-safe singleton. That's all.");
    }
}
