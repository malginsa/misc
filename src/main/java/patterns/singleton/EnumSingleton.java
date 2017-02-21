package patterns.singleton;

public class EnumSingleton {

    private enum Counter {
        INSTANCE;
        private int count;

        public synchronized int getNextCount() {
            return ++count;
        }
    }

    public static void main(String[] args) {
        System.out.println(Counter.INSTANCE.getNextCount());
        System.out.println(Counter.INSTANCE.getNextCount());
    }

}
