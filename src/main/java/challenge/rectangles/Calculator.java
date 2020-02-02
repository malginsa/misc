package challenge.rectangles;

import java.util.Collection;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.groupingBy;

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

    public static int optimized(Collection<Point> points)
    {
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(points);
        Map<Long, Long> collect = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(groupingBy(PairOfPointsWithSameY::getUniqId, Collectors.counting()));

        int summ = 0;
        for (Long value : collect.values())
        {
            long pairsCount = value * (value - 1) / 2;
            summ += pairsCount;
        }

        return summ;
    }
}
