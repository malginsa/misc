package quizful;

public class ConstructorOfSuper {

    class B {
        private B() {
            System.out.print("1");
        }

        protected B(String str) {
            System.out.print("2");
        }
    }

    class A extends B {
        private A() {
            this("smth");
        }

        public A(String str) {
        }

    }

    public static void main(String[] args) {
        A a = new ConstructorOfSuper().new A();
    }

}
