package quizful;

public class ClassLoading {

    static class A {
        static {
            System.out.print("A");
        }
    }

    static class B extends A {
        public static int x = 5;

        static {
            System.out.print("B");
        }
    }

    static class C extends B {
        static {
            System.out.print("C");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        System.out.print(C.x); // AB5
    }
}
