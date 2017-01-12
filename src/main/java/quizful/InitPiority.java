package quizful;

public class InitPiority {
    public static InitPiority instance = new InitPiority();
    private static final int DELTA = 5;
    private static int BASE = 7;
    private int x;

    public InitPiority() {
        x = BASE + DELTA;
    }

    public static void main(String[] args) {
        System.out.println(InitPiority.instance.x);
    }
}
