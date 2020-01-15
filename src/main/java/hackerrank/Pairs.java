package hackerrank;

import java.util.Arrays;

public class Pairs {

    static int pairs(int k, int[] arr) {
        int result = 0;
        Arrays.sort(arr);
        int i = 0;
        int j = 1;
        while(j < arr.length) {
            int diff = arr[j] - arr[i];
            if (diff < k) j++;
            else if (diff > k) i++;
            else {
                i++; j++; result++;
            }
        }
        return result;
    }

}
