package hackerrank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairsTest {

    @Test
    public void pairs(){
        Pairs pairs = new Pairs();
        int actual = pairs.pairs(2, new int[]{1, 5, 3, 4, 2});
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }
}