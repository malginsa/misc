package patterns.singleton;

public class Lazy {

    private volatile static Lazy instance;

    private Lazy() {
    }

    public static Lazy getInstance() {
        if (null == instance) {
            synchronized (Lazy.class) {
                if (null == instance) {
                    instance = new Lazy();
                }
            }
        }
        return instance;
    }
}
