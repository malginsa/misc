package quizful;

public class FieldNotOverloaded {

    static class A {
        public String str;
    }

    static class B extends A {
        public String str;
    }

    public static void main(String[] args) {
        B b = new B();
        b.str = "b";
        A a = b;
        a.str = "ab";
        System.out.println(b.str);
        System.out.println(a.str);
    }
}
