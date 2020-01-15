package misc;

import java.util.regex.Pattern;

public class RegSample
{
    private static final Pattern SEC_QUALIDFIER = Pattern.compile("Sec\\.\\s(\\d+)\\.\\$#emsp;");

    public static void main(String[] args)
    {
//        System.out.println(SEC_QUALIDFIER.matcher("Sec. 1.$#emsp;").matches());
//        System.out.println(SEC_QUALIDFIER.matcher("Sec. 1.$#emsp;_").matches());
//        System.out.println(SEC_QUALIDFIER.matcher("_Sec. 1.$#emsp;_").matches());

        System.out.println("AN ACT1".replaceFirst("(?<=AN ACT)(?!\\s)", " "));
        System.out.println("AN ACT 2".replaceFirst("(?<=AN ACT)(?!\\s)", " "));
        System.out.println("|" + "AN ACT".replaceFirst("(?<=AN ACT)(?!\\s|$)", " ") + "|");
    }
}
