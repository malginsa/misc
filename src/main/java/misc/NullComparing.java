package misc;

import java.util.Optional;

public class NullComparing {

    public static void main(String[] args) {

        String s1 = null;
        String s2 = null;

        System.out.println(s1 == s2); // true
        System.out.println(null == null); // true

        Optional<String> opt1 = Optional.empty();
        Optional<String> opt2 = Optional.empty();

        System.out.println(opt1 == opt2); // true
    }
}
