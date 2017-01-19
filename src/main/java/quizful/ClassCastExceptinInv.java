package quizful;

public class ClassCastExceptinInv {
    public static void main(String[] args) {
        A9 a = new A9();
        Single s = new Single();
        Double2 d = new Double2();
        d = (Double2) a; // 1
        d = (Double2) s; // 2
        s = (Single) a; // 3
        s = (Single) d; // 4
        a = (A9) d; // 5
        a = (A9) s; // 6
    }
}

class A9 {}

class Single extends A9 {}

class Double2 extends Single {}
