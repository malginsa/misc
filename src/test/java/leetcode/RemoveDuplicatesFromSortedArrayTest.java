package leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RemoveDuplicatesFromSortedArrayTest {

    private RemoveDuplicatesFromSortedArray instance;

    @Before
    public void init() {
        instance = new RemoveDuplicatesFromSortedArray();
    }

    @Test
    public void emptyArray()
    {
        int[] input = {};
        int[] expected = {};
        doRemoveDuplicates(input, expected);
    }

    @Test
    public void oneElement()
    {
        int[] input = {1};
        int[] expected = {1};
        doRemoveDuplicates(input, expected);
    }

    @Test
    public void allElementsAreEquals()
    {
        int[] input = {1, 1, 1, 1, 1};
        int[] expected = {1};
        doRemoveDuplicates(input, expected);
    }

    @Test
    public void allElementsAreDistinct()
    {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        doRemoveDuplicates(input, expected);
    }

    @Test
    public void mixed()
    {
        int[] input = {1, 2, 2, 3, 4, 4, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        doRemoveDuplicates(input, expected);
    }

    private void doRemoveDuplicates(int[] input, int[] expected) {
        int length = instance.removeDuplicates(input);
        int[] result = Arrays.copyOf(input, length);
        assertEquals(result.length, length);
        assertArrayEquals(expected, result);
    }
}