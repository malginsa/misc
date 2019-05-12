package hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class MissingNumbersTest {

    @Test
    public void missingNumbers() {
        MissingNumbers missingNumbers = new MissingNumbers();
        int[] actual = missingNumbers.missingNumbers(
                new int[]{203, 204, 205, 206, 207, 208, 203, 204, 205, 206},
                new int[]{203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204});
        int[] expected = new int[]{204, 205, 206};
        Assert.assertArrayEquals(expected, actual);
    }
}