package hackerrank;

public class MaximumSubarraySum {

    static long bruteForce(long[] a, long m) {
        long result = 0;
        for (int leftIndex = 0; leftIndex < a.length; leftIndex++) {
            for (int rightIndex = leftIndex + 1; rightIndex < a.length + 1; rightIndex++) {
                long sum = 0;
                for (int i = leftIndex; i < rightIndex; i++) {
                    sum += a[i];
                }
                long module = sum % m;
                if (module > result) result = module;
            }
        }
        return result;
    }

//    static long maximumSum(long[] a, long m) {
//        return 0;
//    }

}
