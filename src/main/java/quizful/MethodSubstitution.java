package quizful;

public class MethodSubstitution {
    public static void main(String[] args) {
        A8 a = new B8();
        a.test(new Integer(5));
    }
}

class A8 {
    public void test(Object obj) {
        System.out.println("Object");
    }
}

class B8 extends A8 {
    public void test(Integer obj) {
        System.out.println("Integer");
    }
}