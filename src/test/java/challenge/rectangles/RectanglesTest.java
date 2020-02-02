package challenge.rectangles;

import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

import static challenge.rectangles.PointsCollectionUtils.readPredefinedSet;
import static org.junit.Assert.assertEquals;

public class RectanglesTest
{
    @Test
    public void empty()
    {
        assertEquals(0, Calculator.bruteForce(new HashSet<>()));
    }

    @Test
    public void treePoints()
    {
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 3));
        points.add(new Point(3, 3));
        assertEquals(0, Calculator.bruteForce(points));
    }

    @Test
    public void oneRectangle()
    {
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 3));
        points.add(new Point(3, 1));
        points.add(new Point(3, 3));
        assertEquals(1, Calculator.bruteForce(points));
    }

    @Test
    public void twoRectangles()
    {
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 3));
        points.add(new Point(3, 1));
        points.add(new Point(3, 3));
        points.add(new Point(10, 10));
        points.add(new Point(10, 13));
        points.add(new Point(13, 10));
        points.add(new Point(13, 13));
        assertEquals(2, Calculator.bruteForce(points));
    }

    @Test
    public void threeOverlappingRectangles()
    {
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 3));
        points.add(new Point(3, 1));
        points.add(new Point(3, 3));
        points.add(new Point(4, 1));
        points.add(new Point(4, 3));
        assertEquals(3, Calculator.bruteForce(points));
    }

    @Test
    public void nineOverlappingRectangles()
    {
        HashSet<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        points.add(new Point(1, 4));
        points.add(new Point(2, 1));
        points.add(new Point(2, 2));
        points.add(new Point(2, 4));
        points.add(new Point(5, 1));
        points.add(new Point(5, 2));
        points.add(new Point(5, 4));
        points.add(new Point(3, 3));
        assertEquals(9, Calculator.bruteForce(points));
    }

    @Test
    public void test10pointOn5x5() throws IOException
    {
        testPredefinedSetNxN(5, 5, 2);
    }

    @Test
    public void test100pointOn20x20() throws IOException
    {
        testPredefinedSetNxN(20, 20, 181);
    }

    @Test
    public void test1000pointOn50x50() throws IOException
    {
        testPredefinedSetNxN(50, 50, 38014);
    }

    @Test
    public void test10000pointOn300x300() throws IOException
    {
        testPredefinedSetNxN(300, 300, 307683);
    }

    public static void testPredefinedSetNxN(int xSize, int ySize, int countOfExpectedRectangles) throws IOException
    {
        HashSet<Point> points = readPredefinedSet(xSize, ySize);
        assertEquals(countOfExpectedRectangles, Calculator.bruteForce(points));
    }
}