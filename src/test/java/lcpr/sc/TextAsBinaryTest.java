package lcpr.sc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TextAsBinaryTest {

    @Test
    public void testCountMatchesZero() {
        TextAsBinary binaryAsText = new TextAsBinary(new byte[] {});
        int matches = binaryAsText.countMatches(new byte[] {1});
        Assert.assertEquals(0, matches);
    }

    @Test
    public void testCountMatches() {
        TextAsBinary binaryAsText = new TextAsBinary(new byte[] {1, 2, 3, 1, 1, 2, 1, 2, 1});
        int matches = binaryAsText.countMatches(new byte[] {1, 2});
        Assert.assertEquals(3, matches);
    }

    @Test
    public void testLineDelimiterWindows() {
        TextAsBinary textAsBinary = new TextAsBinary(new byte[] {31, 13, 10, 13, 10, 13, 10, 31, 32, 33, 10});
        Assert.assertArrayEquals(new byte[]{13, 10}, textAsBinary.getLineDelimiter());
    }

    @Test
    public void testLineDelimiterUnix() {
        TextAsBinary textAsBinary = new TextAsBinary(new byte[] {31, 10, 13, 10, 10, 31, 32, 33, 10});
        Assert.assertArrayEquals(new byte[]{13, 10}, textAsBinary.getLineDelimiter());
    }

    @Test
    public void testNextLine1() {
        TextAsBinary textAsBinary = new TextAsBinary(new byte[] {31, 13, 10, 13, 10, 13, 10, 31, 32, 33, 10});
        byte[] line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        System.out.print(line);
    }

    @Test
    public void testNextLine2() {
        TextAsBinary textAsBinary = new TextAsBinary(new byte[] {31, 13, 10, 13, 10, 13, 10});
        byte[] line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        System.out.print(line);
    }

    @Test
    public void testNextLine3() {
        TextAsBinary textAsBinary = new TextAsBinary(new byte[] {31, 13, 10});
        byte[] line = textAsBinary.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = textAsBinary.nextLine();
        System.out.print(line);
    }
}