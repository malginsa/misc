package quizful;

abstract class AbstractInterface {
    protected abstract int doSmth(String s);
}

//abstract class X implements AbstractInterface {}

class Z extends AbstractInterface {
    protected int doSmth(String s) {
        return 0;
    }
}
