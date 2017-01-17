package quizful;

class Parent2 {
    public Parent2(String s) {
        System.out.println("in Parent(String)");
    }
    public Parent2() {
        System.out.println("in Parent()");
    }
}

class Child2 extends Parent2{
    public Child2(String s) {
//        super("");
        System.out.println("in Child(String)");
    }
    public Child2() {
        System.out.println("in Child()");
    }
}

public class ConstructorsInvocation {
    public static void main(String[] args) {
        new Child2("");
    }
}
