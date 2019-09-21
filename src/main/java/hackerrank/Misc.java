package hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Misc {

    public static void main(String[] args) {
//        System.out.println(compareTriplets(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1)));
//        System.out.println(aVeryBigSum(new long[]{1000000001, 1000000002, 1000000003, 1000000004, 1000000005}));
//        plusMinus(new int[]{1,2,-3});
//        staircase(6);
//        miniMaxSum(new int[]{1,3,5,7,9});
        TreeMap<Integer, Integer> map = new TreeMap<>();
//        map.floorKey()
    }

    static int birthdayCakeCandles(int[] ar) {
        int max = IntStream.of(ar).max().getAsInt();
        return (int)IntStream.of(ar).filter(element -> element == max).count();
    }

    static void miniMaxSum(int[] arr) {
        long min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < min) min = arr[i];
            if(arr[i] > max) max = arr[i];
            sum += arr[i];
        }
        System.out.println("" + (sum - max) + " " + (sum - min));
    }

    static void staircase(int n) {
        for (int i = 0; i < n; i++) {
            String spaces = new String(new char[n - i - 1]).replace('\0', ' ');
            String hashes = new String(new char[i + 1]).replace('\0', '#');
            System.out.println(spaces + hashes);
        }
    }

    static int diagonalDifference(int[][] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i][i] - arr[arr.length - i - 1][i];
        }
        return Math.abs(result);
    }

    static void plusMinus(int[] arr) {
        float plus = 0, minus = 0, zeros = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) plus++;
            else if (arr[i] == 0) zeros++;
            else minus++;
        }
        System.out.printf("%.6f\n", plus/arr.length);
        System.out.printf("%.6f\n", minus/arr.length);
        System.out.printf("%.6f\n", zeros/arr.length);
    }

    static long aVeryBigSum(long[] ar) {
        return LongStream.of(ar).sum();
    }

    private static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        Integer[] result = new Integer[]{0, 0};
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (a.get(i) > b.get(i)) {
                result[0]++;
            } else if (a.get(i) < b.get(i)) {
                result[1]++;
            }
        }
        return Arrays.asList(result);
    }
}
