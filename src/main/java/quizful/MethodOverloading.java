package quizful;

class A4 {
    public void m(Number n) { // this method is not overloaded in class B
        System.out.println("in class A4 ");
    }
}

class B4 extends A4 {
    public void m(Double d) {
        System.out.println("in class B4 ");
    }
}

public class MethodOverloading {
    public static void main(String args[]) {
        A4 a = new B4();
        a.m(5.0);
    }
}
