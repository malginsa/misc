package hackerrank;

import java.util.Arrays;
import java.util.List;

public class CompareTriplets {

    public static void main(String[] args) {
        System.out.println(compareTriplets(Arrays.asList(1, 2, 3), Arrays.asList(3, 2, 1)));
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
