package hackerrank;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BalancedSumsTest {

    @Test
    public void test1() {
        String actual = BalancedSums.balancedSums(Arrays.asList(1, 2, 3));
        Assert.assertEquals("NO", actual);
    }

    @Test
    public void test2() {
        String actual = BalancedSums.balancedSums(Arrays.asList(1, 2, 3, 3));
        Assert.assertEquals("YES", actual);
    }

    @Test
    public void test3() {
        String actual = BalancedSums.balancedSums(Arrays.asList(1, 1, 4, 1, 1));
        Assert.assertEquals("YES", actual);
    }

    @Test
    public void test4() {
        String actual = BalancedSums.balancedSums(Arrays.asList(2, 0, 0, 0));
        Assert.assertEquals("YES", actual);
    }

    @Test
    public void test5() {
        String actual = BalancedSums.balancedSums(Arrays.asList(0, 0, 2, 0));
        Assert.assertEquals("YES", actual);
    }
}