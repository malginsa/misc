package lcpr.sc;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTextTest {

    @Test
    public void testCountMatchesZero() {
        BinaryText binaryAsText = new BinaryText(new byte[] {});
        int matches = binaryAsText.countMatches(new byte[] {1});
        Assert.assertEquals(0, matches);
    }

    @Test
    public void testCountMatches() {
        BinaryText binaryAsText = new BinaryText(new byte[] {1, 2, 3, 1, 1, 2, 1, 2, 1});
        int matches = binaryAsText.countMatches(new byte[] {1, 2});
        Assert.assertEquals(3, matches);
    }

    @Test
    public void testLineDelimiterWindows() {
        BinaryText binaryText = new BinaryText(new byte[] {31, 13, 10, 13, 10, 13, 10, 31, 32, 33, 10});
        Assert.assertArrayEquals(new byte[]{13, 10}, binaryText.getLineDelimiter());
    }

    @Test
    public void testLineDelimiterUnix() {
        BinaryText binaryText = new BinaryText(new byte[] {31, 10, 13, 10, 10, 31, 32, 33, 10});
        Assert.assertArrayEquals(new byte[]{13, 10}, binaryText.getLineDelimiter());
    }

    @Test
    public void testNextLine1() {
        BinaryText binaryText = new BinaryText(new byte[] {31, 13, 10, 13, 10, 13, 10, 31, 32, 33, 10});
        byte[] line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        System.out.print(line);
    }

    @Test
    public void testNextLine2() {
        BinaryText binaryText = new BinaryText(new byte[] {31, 13, 10, 13, 10, 13, 10});
        byte[] line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        System.out.print(line);
    }

    @Test
    public void testNextLine3() {
        BinaryText binaryText = new BinaryText(new byte[] {31, 13, 10});
        byte[] line = binaryText.nextLine();
        for (int i = 0; i < line.length; i++) {
            System.out.print(" " + line[i]);
        }
        System.out.println();
        line = binaryText.nextLine();
        System.out.print(line);
    }
}