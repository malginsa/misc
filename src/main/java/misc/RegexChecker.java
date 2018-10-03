package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker
{

    public static final String LONG_STRING = " Derek Banas CA AK ARGH 12345 PA (412)555-1212 johnsmith";

    public static void main(String[] args)
    {
//        regexChecker("\\s[]A-Za-z]{2,20}\\s", LONG_STRING);
        regexChecker("A[KLRZ]|C[AOT]", LONG_STRING);
    }

    public static void regexChecker(String regex, String str2Check)
    {
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str2Check);

        while (matcher.find())
        {
            String group = matcher.group();
            if (group.length() != 0)
            {
                System.out.println("group: " + group.trim());
            }
            System.out.println("start index: " + matcher.start());
            System.out.println("end index: " + matcher.end());
        }
    }
}
