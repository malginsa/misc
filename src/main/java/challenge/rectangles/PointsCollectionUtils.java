package challenge.rectangles;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class PointsCollectionUtils
{
    public static final String PATH_TO_PREDEFINED_SETS = "src/test/resources/challenge/rectangles/";

    @NotNull
    public static HashSet<Point> readPredefinedSet(final int xSize, final int ySize) throws IOException
    {
        List<String> strings = FileUtils.readLines(
                new File("src/test/resources/challenge/rectangles/" + xSize + "x" + ySize + ".txt"), Charset.defaultCharset());

        HashSet<Point> points = new HashSet<>();
        for (String string : strings)
        {
            String[] coordinates = string.split("\\s");
            points.add(new Point(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1])));
        }
        return points;
    }

    public static void StoreToFile(final Collection<Point> points, String fileName) throws IOException
    {
        FileUtils.writeLines(new File(PATH_TO_PREDEFINED_SETS + fileName), points, false);
    }
}
