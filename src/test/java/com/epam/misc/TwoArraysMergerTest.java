package com.epam.misc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static com.epam.misc.TwoArraysMerger.merge;
import static org.junit.Assert.*;

public class TwoArraysMergerTest
{
    @Test(expected = IllegalArgumentException.class)
    public void nullAsBothParameters()
    {
        int[] merged = merge(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAsFirstParameter()
    {
        int[] merged = merge(null, new int[]{1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAsSecondParameter()
    {
        int[] merged = merge(new int[]{}, null);
    }

    @Test
    public void emptyArrays()
    {
        int[] merged = merge(new int[]{}, new int[]{});
        Assert.assertEquals(0, merged.length);
    }

    @Test
    public void emptyFirstArray()
    {
        int[] merged = merge(new int[]{}, new int[]{1});
        Assert.assertEquals(1, merged.length);
    }

    @Test
    public void emptySecondArray()
    {
        int[] merged = merge(new int[]{1, 2}, new int[]{});
        Assert.assertEquals(2, merged.length);
    }

    @Test
    public void merge1() throws Exception
    {
        int[] merged = merge(new int[]{1, 3}, new int[]{2, 4});
        Assert.assertArrayEquals(merged, new int[]{1, 2, 3, 4});
    }

    @Test
    public void merge2() throws Exception
    {
        int[] merged = merge(new int[]{1, 3, 5, 6}, new int[]{2, 4});
        Assert.assertArrayEquals(merged, new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void merge3() throws Exception
    {
        int[] merged = merge(new int[]{1, 3,}, new int[]{2, 4, 5, 6});
        Assert.assertArrayEquals(merged, new int[]{1, 2, 3, 4, 5, 6});
    }

}