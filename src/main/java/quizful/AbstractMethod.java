package quizful;

abstract class A{
    abstract void m1();
}

abstract class B extends A{}

class C extends B {

    @Override
    void m1() {

    }
}

public class AbstractMethod {
}
