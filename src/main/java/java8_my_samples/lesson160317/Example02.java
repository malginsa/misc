package java8_my_samples.lesson160317;

//Method references:
//        Static   RefType::staticMethod     (args) -> RefType.staticMethod(args)
//        Bound Instance  expr::instMethod   (args) -> expr.instMethod(args)
//        Unbound Instance  RefType::instMethod  (arg0, rest) -> arg0.instMethod(rest)
//        Constructor       ClassName::new   (args) -> new ClassName(args)

import java.util.Arrays;

public class Example02 {

    public static void main(String[] args) {

        String[] a = {"one", "two", "three", "four"};

        Arrays.asList(a).forEach(System.out::println);
        // Bound Instance  expr::instMethod  (args) -> expr.instMethod(args)
    }
}
