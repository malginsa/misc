package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MissingNumbers {

    public int[] missingNumbers(int[] arr, int[] brr) {
        int min = Arrays.stream(brr).parallel().min().getAsInt();
        Map<Integer, Long> arrHistogram = createHistogram(arr, min);
        Map<Integer, Long> brrHistogram = createHistogram(brr, min);
        List<Integer> result = new ArrayList<>();
        for (Integer number : brrHistogram.keySet()) {
            if (arrHistogram.get(number) == null
                    || brrHistogram.get(number) > arrHistogram.get(number))
                result.add(number);
        }
        return result.stream().mapToInt(el -> el+min).toArray();
    }

    private static Map<Integer, Long> createHistogram(int[] brr, int min) {
        return Arrays.stream(brr)
                .map(el -> el - min)
                .boxed()
                .collect(Collectors.groupingBy(el->el, Collectors.counting()));
    }
}
