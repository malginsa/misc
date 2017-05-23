package patterns.singleton;

public enum EnumSingleton {

    INSTANCE;
    private int count;

    public synchronized int getNextCount() {
        return ++count;
    }

    public static void main(String[] args) {
        System.out.println(EnumSingleton.INSTANCE.getNextCount());
        System.out.println(EnumSingleton.INSTANCE.getNextCount());
    }

}
