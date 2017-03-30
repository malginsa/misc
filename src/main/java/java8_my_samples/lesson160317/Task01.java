package java8_my_samples.lesson160317;

import java.util.Arrays;

public class Task01 {

    public static void main(String[] args) {

        String[] a = {"one", "two", "three", "four"};

//        Arrays.sort(a, (s1, s2) -> s1.length() - s2.length());
//        Arrays.sort(a, (s1,s2) -> s2.length() - s1.length());
//        Arrays.sort(a, (s1,s2) -> s1.charAt(1) - s2.charAt(1));

        Arrays.sort(a, (s1,s2) -> {
            if (s1.charAt(0) != 't' || s2.charAt(0) != 't') {
                return 0;
            }
            return s1.charAt(1) - s2.charAt(1);
        });

        System.out.println(Arrays.toString(a));

        new Thread(()-> System.out.println("hello")).start();

        new Thread(System.out::println).start();
    }
}
