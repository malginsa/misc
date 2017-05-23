package patterns.singleton;

public class Greedy {

    // It is thread-safe, because creation of an instance of Greedy
    // and creation this field "INSTANCE" will placed in the same thread.
    private static Greedy INSTANCE;

    private Greedy() {
    }

    static {
        try {
            INSTANCE = new Greedy();
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred during Greedy singleton creation");
        }
    }

    public static Greedy getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Greedy.getInstance() == Greedy.getInstance());
    }
}
