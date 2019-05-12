package hackerrank.minimumLoss;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MinimumLossTest {

    private static BruteForce bruteForce;
    private static UsingBST usingBST;
    private static Collection<Data> presetData = new ArrayList<Data>() {{
            add (new Data(1, new long[]{3, 2, 6}));
            add (new Data(3, new long[]{9, 10, 6, 2, 3}));
            add (new Data(3, new long[]{62, 28, 93, 87, 55, 59, 98, 50, 1, 43}));
    }};
    private static Collection<Data> dynamicData = new ArrayList<Data>();

    @BeforeClass
    public static void init() {
        bruteForce = new BruteForce();
        usingBST = new UsingBST();

        long[] prices = generatePrices(10, 100);
        int loss = bruteForce.calculate(prices);
        dynamicData.add(new Data(loss, prices));

        prices = generatePrices(20000, 200000);
        loss = bruteForce.calculate(prices);
        dynamicData.add(new Data(loss, prices));
    }

    @Test
    public void presetData() {
        for (Data data : presetData) {
            Assert.assertEquals("prices: " + Arrays.toString(data.prices),
                    data.minLoss, usingBST.calculate(data.prices));
        }
    }

    @Test
    public void dynamicData() {
        for (Data data : dynamicData) {
            Assert.assertEquals("prices: " + Arrays.toString(data.prices),
                    data.minLoss, usingBST.calculate(data.prices));
        }
    }

    @Test
    public void input11DataTest() {
        int actual = usingBST.calculate(input11Data());
        Assert.assertEquals(47175, usingBST.calculate(input11Data()));
    }

    public long[] input11Data() {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get(
                    "src/test/resources/hackerrank.minimumLoss/input11.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        return Arrays.stream(strings.get(1).split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    private static long[] generatePrices(int count, int max){
        return new Random()
                .longs(count, 1, max + 1)
                .distinct()
                .toArray();
    }

    private static class Data {
        int minLoss;
        long[] prices;
        public Data(int minLoss, long[] prices) {
            this.minLoss = minLoss;
            this.prices = prices;
        }
    }
}
