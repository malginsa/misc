package patterns.singleton;

public class EnumSingleton {

    private enum Singleton {
        INSTANCE;
        private int count;
        public synchronized int getNextCount() {return ++count;}
    }

    public static void main(String[] args) {
        System.out.println( Singleton.INSTANCE.getNextCount());
        System.out.println( Singleton.INSTANCE.getNextCount());
    }

}
