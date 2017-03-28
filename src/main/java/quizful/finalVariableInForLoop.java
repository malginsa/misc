package quizful;

public class finalVariableInForLoop {
    public static void main(String[] args) {
        for (final int i: new int[] {1,2,3}) {
            System.out.println(i+1);
        }
    }
}
