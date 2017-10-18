package lcpr.sc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeriodSpace {

    private static Pattern BLANK_LINE = Pattern.compile("^\\*+$");
    private static final String PERIOD_SPACE= "\\. ";

    public static void main(String[] args) {

        String s = " Senate. Each appointment shall be for a term of six\n" +
                "years or until a successor is appointed and qualified. The members of the ";
        String s1 = s.replaceAll(PERIOD_SPACE, ".ensp");
        System.out.println("|" + s1 + "|");

    }
}
