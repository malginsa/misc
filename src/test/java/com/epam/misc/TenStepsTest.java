package com.epam.misc;

import org.junit.Test;

import static org.junit.Assert.*;

public class TenStepsTest {
    @Test
    public void getCount() throws Exception {
        assertEquals("negative number", TenSteps.getCount(-7), 0);
        assertEquals("zero", TenSteps.getCount(0), 0);
        assertEquals("1", TenSteps.getCount(1), 1);
        assertEquals("2", TenSteps.getCount(2), 2);
        assertEquals("3", TenSteps.getCount(3), 4);
        assertEquals("4", TenSteps.getCount(4), 7);
        assertEquals("5", TenSteps.getCount(5), 13);
    }

}