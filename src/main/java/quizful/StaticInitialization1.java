package quizful;

public class StaticInitialization1 {

    static class A extends B {
        static Integer q = 2;
        static {
            System.out.print("A");
            A.q = 4;
        }
    }

    static class B {
        static {
            System.out.print("B");
            A.q++;
        }
    }

    public static void main(String[] args) {
        Integer q = null;
        try {
            q = A.q;
        } catch (ExceptionInInitializerError e) {
            System.out.println("EXCEPTION: ExceptionInInitializerError");
        }
        System.out.println(q);
    }
}

