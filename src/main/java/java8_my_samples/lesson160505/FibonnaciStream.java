package java8_my_samples.lesson160505;

import java8_my_samples.lesson160504.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonnaciStream {

    private static int limit = 17;

    public static void main(String[] args) {

        // 0
        int v1 = 0;
        int v2 = 1;
        int v3;
        for (int i = 0; i < limit; i++) {
            v3 = v1;
            v1 = v2;
            v2 += v3;
            System.out.print(" " + v1);
        }
        System.out.println();

        // 1
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1],t[0]+t[1]})
                .limit(limit)
                .map(t -> " " + t[1])
                .forEach(System.out::print);
        System.out.println();

        // 2
        IntSupplier fibSupplier = new IntSupplier() {
            private int v1 = 0;
            private int v2 = 1;
            private int v3;
            @Override
            public int getAsInt() {
                v3 = v1 + v2;
                v1 = v2;
                v2 = v3;
                return v1;
            }
        };
        IntStream.generate(fibSupplier)
                .limit(limit)
                .boxed()
                .map(f -> " " + f)
                .forEach(System.out::print);

    }
}
