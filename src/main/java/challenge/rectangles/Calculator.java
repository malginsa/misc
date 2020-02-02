package challenge.rectangles;

import java.util.Collection;

public class Calculator
{
    public static int bruteForce(Collection<Point> points)
    {
        int result = 0;
        for (Point point : points)
        {
            for (Point point2 : points)
            {
                if (point2.isAboveAndOnRight(point))
                {
                    if (points.contains(new Point(point2.x, point.y))
                            && (points.contains(new Point(point.x, point2.y))))
                    {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
