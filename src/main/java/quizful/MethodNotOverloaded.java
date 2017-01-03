package quizful;

class Parent {
    private void print() {
        System.out.println("in Parent");
    }
    void print(Parent p) {
        p.print();
    }
}

class Child extends Parent {
    void print() { // this method is not overloaded the same in Parent, cause that is private
        System.out.println("in Child");
    }
}


public class MethodNotOverloaded {
    public static void main(String[] args) {
        Parent p = new Parent();
        Child q = new Child();
        p.print(q); // in Parent
    }
}
