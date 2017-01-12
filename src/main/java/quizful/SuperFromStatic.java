package quizful;

class SuperFromStaticParent {
    private static void test1() {
        System.out.println("Parent");
    }
    static void test2() {
        test1();
    }
}

public class SuperFromStatic extends SuperFromStaticParent {
    static void test1() {
        System.out.println("Child");
    }
    static void test2() {
//        super.test2();
        SuperFromStaticParent.test2();
    }
    public static void main(String[] args) {
        SuperFromStaticParent a = new SuperFromStatic();
        a.test2();
    }
}