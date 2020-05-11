package leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReverseIntegerTest{

    private ReverseInteger instance;

    @Before
    public void init() {
        instance = new ReverseInteger();
    }

    @Test
    public void MinInteger(){
        int result = instance.reverse(Integer.MIN_VALUE);
        Assert.assertEquals(0,result);
    }

    @Test
    public void OneDigit(){
        int result = instance.reverse(7);
        Assert.assertEquals(7,result);
    }

    @Test
    public void NegativeOneDigit(){
        int result = instance.reverse(-5);
        Assert.assertEquals(-5,result);
    }

    @Test
    public void testPositiveNumber(){
        int result = instance.reverse(7311);
        Assert.assertEquals(1137,result);
    }

    @Test
    public void testNegativeNumber(){
        int result = instance.reverse(-7311);
        Assert.assertEquals(-1137,result);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void OverflowsPositive(){
        int result = instance.reverse(1534236469);
        Assert.assertEquals(0,result);
    }

    @Test
    public void OverflowsNegative(){
        int result = instance.reverse(-2147483648);
        Assert.assertEquals(0,result);
    }
}