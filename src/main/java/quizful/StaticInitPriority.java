package quizful;

public class StaticInitPriority {
    public static void main(String[] args) {
        System.out.print(A6.i);
        System.out.print(B6.i);
    }
}

class A6 {
    static {
        i = 2;
    }
    static int i = 1;
}

class B6 {
    static int i = 1;
    static {
        i = 2;
    }
}
