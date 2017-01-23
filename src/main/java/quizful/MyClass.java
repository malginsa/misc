package quizful;

public class MyClass {

    static class A implements Cloneable {
        public int i = 10;
    }

    static class B extends A implements Cloneable {
        public int i = 20;

        @Override
        public B clone() throws CloneNotSupportedException {
            B cloneA = (B) super.clone();
            cloneA.i = 15;
            return cloneA;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        B b = new B();
        A a = b.clone();
        System.out.println(a.i); //10
    }
}
