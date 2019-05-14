package hackerrank;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class BalancedSums {

    static String balancedSums(List<Integer> arr) {
        if (arr.size() < 2) return "YES";
        long leftSum = 0L;
        Iterator<Integer> iterator = arr.iterator();
        Integer separationElement = iterator.next();
        LongAdder a = new LongAdder();
        arr.stream().forEach(a::add);
        long rightSum = a.longValue() - separationElement;
        while (iterator.hasNext()) {
            if (leftSum == rightSum) return "YES";
            leftSum += separationElement;
            separationElement = iterator.next();
            rightSum -= separationElement;
        }
        return "NO";
    }

}
