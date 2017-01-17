package quizful;

class A5 {
    Number method() {
        return new Integer(7);
    }
}

class B5 extends A5 {
    @Override
    Double method() {
        return new Double("7.7");
    }
}

public class OverloadingReturnType {
    public static void main(String[] args) {
        A5 b5 = new B5();
        System.out.println(b5.method()); // 7.7
    }
}
