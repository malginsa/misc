package challenge.rectangles;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Point implements Comparable<Point>
{
    int x, y;

    public Point(final int x, final int y)
    {
        this.x = x;
        this.y = y;
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
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    public boolean isAboveAndOnRight(final Point other)
    {
        return this.x < other.x && this.y < other.y;
    }

    @Override
    public String toString()
    {
        return "" + x + ' ' + y;
    }

    @Override
    public int compareTo(@NotNull final Point other)
    {
        if (this.y == other.y){
            return Integer.compare(this.x, other.x);
        } else {
            return Integer.compare(this.y, other.y);
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(final int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(final int y)
    {
        this.y = y;
    }
}
