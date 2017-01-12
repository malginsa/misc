package quizful;

public class StaticInitPriority {
    public static void main(String[] args) {
        System.out.print(A.i);
        System.out.print(B.i);
    }
}

class A {
    static {
        i = 2;
    }
    static int i = 1;
}

class B {
    static int i = 1;
    static {
        i = 2;
    }
}
