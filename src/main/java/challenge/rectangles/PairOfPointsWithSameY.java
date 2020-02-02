package challenge.rectangles;

import java.util.Objects;

public class PairOfPointsWithSameY
{
    private static final int MAX_X = 1_000;

    private int x1, x2, y;

    public PairOfPointsWithSameY(Point point1, Point point2)
    {
        y = point1.y;
        if (point1.x < point2.x)
        {
            x1 = point1.x;
            x2 = point2.x;
        } else {
            x1 = point2.x;
            x2 = point1.x;
        }
    }

    public long getUniqId()
    {
        return x1 * MAX_X + x2;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        PairOfPointsWithSameY that = (PairOfPointsWithSameY) o;
        return x1 == that.x1 &&
                x2 == that.x2;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x1, x2);
    }

    @Override
    public String toString()
    {
        return "PairOfPointsWithSameY{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y=" + y +
                '}';
    }
}
