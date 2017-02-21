package quizful;

public class CheckInteger {

    public static void main(String[] args) {

        Integer first = Integer.valueOf(5);
        Integer second = Integer.valueOf(5);

        System.out.println(first == second);

        first = Integer.valueOf(5);
        second = Integer.valueOf(5);

        first++;
        second++;

        System.out.println(first == second);

        first = Integer.valueOf(500);
        second = Integer.valueOf(500);

        System.out.println(first == second);

        first = new Integer(5);
        second = new Integer(5);

        System.out.println(first == second);

    }
}
