package quizful;

public class MethodSubstitution {
    public static void main(String[] args) {
        A8 a = new B8();
        a.test(new Integer(5)); // Object
    }
}

class A8 {
    void test(Object obj) {
        System.out.println("Object");
    }
}

class B8 extends A8 {
    void test(Integer obj) {
        System.out.println("Integer");
    }
}
