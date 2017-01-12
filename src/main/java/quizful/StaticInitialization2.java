package quizful;

class A3 {
    static {
        i = 2;
    }

    static int i = 1;
};

class B3 {
    static int i = 1;

    static {
        i = 2;
    }
};

public class StaticInitialization2 {

    public static void main(String[] args) {
        System.out.print(A3.i);
        System.out.print(B3.i);
    }

}
