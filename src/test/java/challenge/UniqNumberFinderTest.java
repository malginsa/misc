package challenge;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class UniqNumberFinderTest {

    @Test
    public void uniqNumberPresented() {
        UniqNumberFinder instance = new UniqNumberFinder();
        List<Long> list = LongStream.range(1, 1_000_000).boxed().collect(Collectors.toList());
        list.addAll(list);
        Long uniqNumber = 777_771L;
        list.add(uniqNumber);
        Collections.shuffle(list);
        Long actual = instance.findUniqNumberUsingBarChart(list).get();
        assertEquals(uniqNumber, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void uniqNumberAbsent() {
        UniqNumberFinder instance = new UniqNumberFinder();
        List<Long> list = LongStream.range(1, 1_000_000).boxed().collect(Collectors.toList());
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        Collections.shuffle(list);
        Long actual = instance.findUniqNumberUsingBarChart(list).get();
    }

}