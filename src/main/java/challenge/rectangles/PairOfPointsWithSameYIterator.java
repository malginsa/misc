package challenge.rectangles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PairOfPointsWithSameYIterator implements Iterator<PairOfPointsWithSameY>
{

    private Map<Integer, List<Point>> pointsBySameY;
    private Iterator<Integer> iteratorByY;
    private List<Point> row = new ArrayList<>(); // points with the same Y-coordinate
    private int currentY; // Y-coordinate of points in current "row"
    private boolean noMorePairs;
    private int indexCurrentPointInRow;
    private int indexLeadingPointInRow;

    public PairOfPointsWithSameYIterator(Collection<Point> points)
    {
        pointsBySameY = points.stream().sorted().collect(groupingBy(Point::getY));
        iteratorByY = pointsBySameY.keySet().iterator();
        getNextRow();
    }

    private void getNextRow()
    {
        noMorePairs = true;
        if (iteratorByY.hasNext())
        {
            currentY = iteratorByY.next();
            row = pointsBySameY.get(currentY);
            if (row.size() < 2)
                getNextRow();
            else {
                noMorePairs = false;
                indexCurrentPointInRow = 0;
                indexLeadingPointInRow = 1;
            }
        }
    }

    private void findNextPair()
    {
        if (indexLeadingPointInRow - indexCurrentPointInRow > 1) indexCurrentPointInRow++;
        else{
            if (indexLeadingPointInRow < (row.size() - 1)){
                indexLeadingPointInRow++;
                indexCurrentPointInRow = 0;
            } else
                getNextRow();
        }
    }

    @Override
    public boolean hasNext()
    {
        return !noMorePairs;
    }

    @Override
    public PairOfPointsWithSameY next()
    {
        PairOfPointsWithSameY result = new PairOfPointsWithSameY(row.get(indexCurrentPointInRow), row.get(indexLeadingPointInRow));
        findNextPair();
        return result;
    }
}
