package patterns.softbinding;

public class B {

    private final STService service;

    public B(STService service) {
        this.service = service;
    }

    public void process() {
        service.doSomething();
    }
}
