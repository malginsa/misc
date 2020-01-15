package hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class MaximumSubarraySumTest {

    @Test
    public void test0() {
        long actual = MaximumSubarraySum.bruteForce(new long[]{3, 3, 9, 9, 5}, 7);
        long expected = 6;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test1() {
        long actual = MaximumSubarraySum.bruteForce(new long[]{1, 2, 3}, 2);
        long expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        long actual = MaximumSubarraySum.bruteForce(new long[]{1, 5, 9}, 5);
        long expected = 4;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        long actual = MaximumSubarraySum.bruteForce(new long[]{1, 5}, 3);
        long expected = 2;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        long actual = MaximumSubarraySum.bruteForce(new long[]{1, 2}, 3);
        long expected = 2;
        Assert.assertEquals(expected, actual);
    }

}