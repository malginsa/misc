package quizful;

abstract class AbstractInterface {
    protected abstract int doSmth(String s);
}

//abstract class X implements AbstractInterface {}

class Z1 extends AbstractInterface {
    protected int doSmth(String s) {
        return 0;
    }
}
