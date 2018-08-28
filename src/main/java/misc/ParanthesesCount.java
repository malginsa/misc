package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParanthesesCount
{
    private static final Pattern PAIR_OF_BRACKETS_CONTENT = Pattern.compile("\\((\\d+)\\)");

    public static void main(String[] args)
    {
        Matcher matcher = PAIR_OF_BRACKETS_CONTENT.matcher("(1)(2)(3)");
        int count = 0;
        while (matcher.find())
        {
            count++;
        }
        System.out.println(count);
    }
}
