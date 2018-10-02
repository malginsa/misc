package misc;

import java.util.regex.Pattern;

public class LookbehindInRegex
{
    public static void main(String[] args)
    {
        System.out.println(
                Pattern.compile("(?<=[A-Z])\\$#ndash;(?=[A-Z])")
                        .matcher("A$#ndash;B")
                        .replaceAll("-")
        );
    }
}
