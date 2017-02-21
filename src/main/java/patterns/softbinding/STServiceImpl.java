package patterns.softbinding;

public class STServiceImpl implements STService {

    // It is thread-safe, because creation of an instance
    // and creation field INSTANCE will placed in the same thread.
    private static STServiceImpl INSTANCE = new STServiceImpl();

    private STServiceImpl() {}

    public static STServiceImpl doService() {
        return INSTANCE;
    }

    public static void main(String[] args) {

        final STServiceImpl sTServiceImpl = STServiceImpl.doService();
    }

    @Override
    public void doSomething() {
        System.out.println("I'm a thread-safe singleton. That's all.");
    }
}
