package quizful;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayCopy {

    public static void print2Darray(String descr, int[][] arr){
        System.out.print(descr + " = { ");
        Arrays.stream(arr).forEach(row -> {
            System.out.print("{");
            Arrays.stream(row).forEach(el -> System.out.print("" + el + ","));
            System.out.print("}, ");
        });
        System.out.println("}");
    }

    public static void main(String[] args) {

        int[] arr1 = {7,8,9,0,0,0,0,0,0,0};
        System.arraycopy(arr1, 1, arr1, 0, 1);
        // indices
        IntStream.range(0, 10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // values
        Arrays.stream(arr1).forEach(el -> System.out.print(el + " "));

        System.out.println("\n\tArrays comparison:");
        int [][] i1 = {{1,2,3}, {0,0,0}};
        int [][] i2 = {{1,2,3}, {0,0,0}};
        int [][] i3 = new int[2][3];
        System.arraycopy(i1, 0, i3, 0, i3.length);
        print2Darray("i1", i1);
        print2Darray("i2", i2);
        print2Darray("i3", i3);
        System.out.println(Arrays.equals(i1, i2)); // false
        System.out.println(Arrays.equals(i1, i3)); // true
        // System.arraycopy has copied references from i1 to i3
        System.out.println(Arrays.deepEquals(i1, i2)); // true

    }
}
