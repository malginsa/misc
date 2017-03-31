package java8_my_samples.lesson160317;

//Method references:
//        Static   RefType::staticMethod     (args) -> RefType.staticMethod(args)
//        Bound Instance  expr::instMethod   (args) -> expr.instMethod(args)
//        Unbound Instance  RefType::instMethod  (arg0, rest) -> arg0.instMethod(rest)
//        Constructor       ClassName::new   (args) -> new ClassName(args)

import java.util.Arrays;

public class Example01 {

    public static void main(String[] args) {

        Integer[] a = {10, 20, -30, 23, 16, 3, 91, 0};

        Arrays.sort(a, Integer::compareUnsigned);
        // Static  RefType::staticMethod  (args) -> RefType.staticMethod(args)

        System.out.println(Arrays.toString(a));

    }
}
