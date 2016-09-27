package patterns.singleton.softbinding;

public class A {

    private final STService service;

    public A(STService service) {
        this.service = service;
        B b = new B(service);
        b.process();
    }

    public static void main(String[] args) {

        A a = new A(ThreadSafeSingleton.get());
    }
}
