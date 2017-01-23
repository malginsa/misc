package quizful;

import java.util.Arrays;

public class EarlyBinding {

    static class B extends A {
        public static void doSome(int[]... arar) {
            Arrays.stream(arar).forEach(arr -> System.out.print(arr[0]));
        }
    }

    static class A {
        public static void doSome(int[]... arar) {
            Arrays.stream(arar).forEach(arr -> System.out.print(arr[1]));
        }
    }

    public static void main(String[] args) {
        B ins = new B();
        ins.doSome(new int[]{7, 5, 48}, new int[]{4, 2, 3}); // 74
        A ins2 = (A) ins;
        ins2.doSome(new int[]{7, 5, 48}, new int[]{4, 2, 3}); // 52
    }
}
