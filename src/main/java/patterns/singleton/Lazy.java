package patterns.singleton;

public class Lazy {

    private Lazy() {
    }

    /**
     * When class Lazy is loaded, Inner isn't loaded at once.
     * Inner will be loaded when method getInstance is invoked.
     */

    private static class Inner {
        private static final Lazy INSTANCE = new Lazy();
    }

    public static Lazy getInstance() {
        return Inner.INSTANCE;
    }

}
