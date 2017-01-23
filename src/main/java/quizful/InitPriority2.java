package quizful;

public class InitPriority2 {

    static abstract class A {
        int a = 8;
        public A() {
            show();
        }
//        void show() { System.out.println("show in A " + a); }
        abstract void show();
    }

    static class B extends A {
        int a = 90; // if this line is commented, the result would be 8
        void show() {
            System.out.println("show in B " + a);
        }
    }

    public static void main(String args[]) {
        new B(); // B0
    }

}
