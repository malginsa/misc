package hackerrank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeConversion {

    public String from12to24(String s) {
        String result = "00:00:00";
        Pattern timePattern = Pattern.compile("(\\d{1,2}):(\\d{1,2}):?(\\d{0,2})(AM|PM)");
        Matcher matcher = timePattern.matcher(s);
        if (matcher.matches())
        {
            String hours = matcher.group(1);
            String mins = matcher.group(2);
            String seconds = matcher.group(3);
            if ("12".equals(hours)) {
                hours = "00";
            }
            if ("PM".equals(matcher.group(4))) {
                hours = String.valueOf(Integer.parseInt(hours) + 12);
            }
            result = hours + ':' + mins + ':' + seconds;
        }
        return result;
    }
}
