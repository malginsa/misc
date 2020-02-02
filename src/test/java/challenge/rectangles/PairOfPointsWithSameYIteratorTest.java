package challenge.rectangles;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PairOfPointsWithSameYIteratorTest
{
    @Test
    public void empty(){
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(new HashSet<>());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void noOnePair(){
        Collection<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 3));
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(points);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void onePair(){
        Collection<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 1));
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(points);
        assertTrue(iterator.hasNext());
    }

    @Test
    public void twoPairs(){
        Collection<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 1));
        points.add(new Point(1, 2));
        points.add(new Point(3, 2));
        points.add(new Point(4, 4));
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(points);
        assertTrue(iterator.hasNext());
        PairOfPointsWithSameY foo = iterator.next();
        assertTrue(iterator.hasNext());
        foo = iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void threePairs(){
        Collection<Point> points = new HashSet<>();
        points.add(new Point(1, 1));
        points.add(new Point(3, 1));
        points.add(new Point(4, 1));
        points.add(new Point(5, 5));
        points.add(new Point(2, 6));
        points.add(new Point(9, 6));
        PairOfPointsWithSameYIterator iterator = new PairOfPointsWithSameYIterator(points);
        assertTrue(iterator.hasNext());
        PairOfPointsWithSameY foo = iterator.next();
        assertTrue(iterator.hasNext());
        foo = iterator.next();
        assertTrue(iterator.hasNext());
        foo = iterator.next();
        assertTrue(iterator.hasNext());
        foo = iterator.next();
        assertFalse(iterator.hasNext());
    }
}
