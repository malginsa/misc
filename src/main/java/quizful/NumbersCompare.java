package quizful;

public class NumbersCompare {
    public static void main(String[] args) {

        Integer i1 = 77;
        Integer i2 = 77;
        System.out.println(i1 == i2); // true,
        // cause there are pulls of the first 255 Integer, Short, Byte, Char, Boolean

        i1 = 777;
        i2 = 777;
        System.out.println(i1 == i2); // false
        System.out.println(i1.equals(i2)); // true

        i1 = new Integer(7);
        i2 = new Integer(7);
        System.out.println(i1 == i2); // false

        Double d1 = 1d;
        Double d2 = 1d;
        System.out.println(d1 == d2); // false

        System.out.println("\tStrings:");
        String s1 = new String("first");
        System.out.println(s1 == "first"); // false
        System.out.println(s1 == new String("first")); //false


    }
}
