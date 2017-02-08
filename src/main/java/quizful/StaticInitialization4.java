package quizful;

public class StaticInitialization4 {

    static class X {
        static String x = Y.y;
    }

    private static class Y {
        static String y = X.x;
    }

    public static void main(String[] args) {
        Y.y = "0";
        System.out.println(X.x);
    }
}