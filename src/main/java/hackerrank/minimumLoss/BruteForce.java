package hackerrank.minimumLoss;

public class BruteForce {

    protected int calculate(long[] price) {
        if (price.length < 2) return Integer.MAX_VALUE;
        long result = safeLoss(price[0], price[1]);
        for (int i = 0; i < price.length - 1; i++) {
            for (int j = i + 1; j < price.length; j++) {
                long candidate = safeLoss(price[i], price[j]);
                result = Math.min(result, candidate);
            }
        }
        return (int) result;
    }

    private long safeLoss(long first, long second) {
        long result = first - second;
        if (result < 0) result = Integer.MAX_VALUE;
        return result;
    }

}
