package quizful;

import java.util.ArrayList;

public class ClassDefinition {
    public static void main(String[] args) {

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();

        System.out.println(c1); // class java.util.ArrayList
        System.out.println(c2); // class java.util.ArrayList
        System.out.println(c1 == c2); // true
        System.out.println(c1.equals(c2)); // true

    }
}
