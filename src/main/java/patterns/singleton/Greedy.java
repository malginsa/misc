package patterns.singleton;

public class Greedy {

    // It is thread-safe, because creation of an instance
    // and creation this field "INSTANCE" will placed in the same thread.
    private static Greedy INSTANCE = new Greedy();

    private Greedy() {
    }

    public static Greedy get() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Greedy.get() == Greedy.get());
    }
}
