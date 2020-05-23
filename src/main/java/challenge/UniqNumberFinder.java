package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class UniqNumberFinder {

    public static void main(String[] args) {
        UniqNumberFinder uniqNumber = new UniqNumberFinder();
        List<Long> list = uniqNumber.readNumbers();
//        uniqNumber.doFindUniqNumber(list).ifPresent(System.out::println);
        uniqNumber.findUniqNumberUsingXor(list).ifPresent(System.out::println);
    }

    private List<Long> readNumbers() {
        List<Long> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLong()) {
                list.add(scanner.nextLong());
            }
        }
        return list;
    }

    protected Optional<Long> findUniqNumberUsingXor(List<Long> list) {
        Long result = 0L;
        int zerosCount = 0;
        for (Long number : list) {
            result = result ^ number;
            if (number == 0) zerosCount++;
        }
        if ((result != 0) || (zerosCount % 2 == 1))
            return Optional.of(result);
        else
            return Optional.empty();
    }

    protected Optional<Long> findUniqNumberUsingBarChart(List<Long> list) {
        ConcurrentMap<Long, Long> barChart = list.stream().parallel()
                .collect(
                        Collectors.groupingByConcurrent(
                                el -> el,
                                Collectors.counting()
                        )
                );
        Optional<Long> result = barChart.keySet().parallelStream().
                filter(key -> barChart.get(key) % 2 == 1)
                .findFirst();
        return result;
    }
}
