package quizful;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayCopy {
    public static void main(String[] args) {

        int[] arr1 = new int[10];
        arr1[0] = 7;
        arr1[1] = 8;
        arr1[2] = 9;
        System.arraycopy(arr1, 1, arr1, 0, 3);
        // indices
        IntStream.range(0, 10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // values
        Arrays.stream(arr1).forEach(el -> System.out.print(el + " "));
    }
}
