package challenge.rectangles;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import static challenge.rectangles.PointsCollectionUtils.StoreToFile;

public class Generator
{

    public static void main(String[] args) throws IOException
    {
        generate10000PointsOn300x300();
    }

    public static Collection<Point> generate(int count, int Xsize, int Ysize)
    {
        Random random = new Random();
        HashSet<Point> points = new HashSet<>();
        while (points.size() < count)
        {
            points.add(new Point(random.nextInt(Xsize), random.nextInt(Ysize)));
        }
        return points;
    }

    public static void generateAndStore(int countOfPoints, int xSize, int ySize) throws IOException
    {
        Collection<Point> points = generate(countOfPoints, xSize, ySize);
        StoreToFile(points, xSize + 'x' + ySize + ".txt");
    }

    public static void generate10PointsOn5x5() throws IOException
    {
        generateAndStore(10, 5, 5);
    }

    private static void generate100PointsOn20x20() throws IOException
    {
        generateAndStore(100, 20, 20);
    }

    private static void generate1000PointsOn50x50() throws IOException
    {
        generateAndStore(1000, 50, 50);
    }

    private static void generate10000PointsOn300x300() throws IOException
    {
        generateAndStore(10000, 300, 300);
    }
}
