package quizful;

class Parent3 {
    private void print() { // it can't be overloaded cause it is private
        System.out.println("in Parent");
    }
    void print(Parent3 p) {
        p.print();
    }
}

class Child extends Parent3 {
    void print() { // this method is not overloaded the same in Parent, cause that is private
        System.out.println("in Child");
    }
}

public class MethodNotOverloaded {
    public static void main(String[] args) {
        Parent3 p = new Parent3();
        Child q = new Child();
        p.print(q); // in Parent
    }
}
