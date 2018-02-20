package lcpr.sc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PairTagReplacement {

    public static void main(String[] args) {
        String result = pairTagReplace("-[d+[d--[d++[d---", "\\[d", "centd");
        System.out.println("result: " + result);
    }

    private static String pairTagReplace(String text, String subRegex, String tagName) {
        String OPEN_TAG = "<" + tagName + ">";
        String END_TAG = "</" + tagName + ">";
        StringBuilder sb = new StringBuilder(text);
        Matcher matcher = Pattern.compile("(" + subRegex + ")").matcher(sb);
        String tag = OPEN_TAG;
        while (matcher.find()) {
            int groups = matcher.groupCount();
            for (int i = 1; i <= groups; i++) {
                System.out.println("group " + i + ": " + matcher.group(i));
            }
            sb.replace(matcher.start(), matcher.end(), tag);
            if (OPEN_TAG.equals(tag)) {
                tag = END_TAG;
            } else {
                tag = OPEN_TAG;
            }
            matcher = Pattern.compile("(" + subRegex + ")").matcher(sb);
        }
        return sb.toString();
    }

}
