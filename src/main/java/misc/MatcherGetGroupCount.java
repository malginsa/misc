package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherGetGroupCount
{

    private static final Pattern SSTE = Pattern
            .compile("(\\([a-z]*\\))?(\\([0-9]*\\))?(\\([A-Z]*\\))?(\\([a-z]*\\))?(\\([A-Z]*\\))?(\\([a-z]*\\))?");

    public static void main(String[] args)
    {
        Matcher matcher;
//        matcher = SSTE.matcher("(e)(4)(B)");
//        System.out.println(matcher.matches());
//        System.out.println(matcher.group(1));
//
        matcher = SSTE.matcher("(4)(B)");
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(6));
        System.out.println(matcher.groupCount());
//        System.out.println(matcher.get);

//        matcher = SSTE.matcher("garbage");
//        System.out.println(matcher.matches());
//        System.out.println(matcher.group(1));
//        System.out.println(matcher.group(6));

    }
}
