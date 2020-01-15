package hackerrank.minimumLoss;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class UsingBST {

    protected int calculate(long[] price) {
        Map<Long, Integer> map = new TreeMap<>(); // key - price, value - position
        for (Integer index = 0; index < price.length; index++) {
            map.put(price[index], index);
        }
        Iterator<Long> iterator = map.keySet().iterator();
        long price1 = iterator.next();
        long price2 = iterator.next();
        long result = safeLoss(price1, price2, map);
        while (iterator.hasNext()) {
            price1 = price2;
            price2 = iterator.next();
            long candidate = safeLoss(price1, price2, map);
            if (candidate < result) result = candidate;
        }
        return Math.toIntExact(result);
    }

    protected long safeLoss(long price1, long price2, Map<Long, Integer> map) {
        Integer positionOfPrice1 = map.get(price1);
        Integer positionOfPrice2 = map.get(price2);
        long result;
        if (positionOfPrice1 > positionOfPrice2) result = price2 - price1;
        else result = Integer.MAX_VALUE;
        return result;
    }
}
