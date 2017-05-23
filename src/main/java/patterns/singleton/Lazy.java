package patterns.singleton;

public class Lazy {

    private Lazy() {
    }

    /**
     * 1) When class Lazy is loaded, Inner isn't loaded at once.
     * Inner will be loaded when method getInstance is invoked.
     * 2) JVM guarantee thread-safe loading/creation of Inner.INSTANCE
     */

    private static class Inner {
        private static final Lazy INSTANCE = new Lazy();
    }

    public static Lazy getInstance() {
        return Inner.INSTANCE;
    }

}
