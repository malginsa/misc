package quizful;

public class MethodDinamicLinking {

    interface I {
        void a(Number n);
    }

    static class A implements I {

        public void a(Number n) {
            System.out.println("number");
        }

        public void a(Double n) {
            System.out.println("double");
        }
    }

    public static void main(String[] args) {

        I i = new A();
        i.a(new Double(12d));
    }
}
