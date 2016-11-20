package eckel;

public class Misc {

    public static void main(String[] args) {

        Class<String> stringClass = String.class;

        Class<Integer> integerClass = int.class;

        Class<Integer> integerClass2 = Integer.TYPE;

        System.out.println(integerClass.equals(integerClass2));

        System.out.println(stringClass.equals(integerClass2));
    }
}
