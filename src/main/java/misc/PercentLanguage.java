package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PercentLanguage {

    private static Pattern percentCodePattern = Pattern.compile("%[0-9a-fA-F]{2}");

    private static String[] table = new String[] {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
//           0A    0B    0C    0D    FE    FF    10    11    12    13
    };

    public String transform(String string) {
        StringBuilder res = new StringBuilder();
        char[] chars = string.toCharArray();
        int i = 0;
        while (i < chars.length) {
            int index = getPercentCodeAt(i, chars);
            if ((index >= 0) && (index <=255)) {
                res.append(table[index]);
                i += 3;
            } else {
                res.append(chars[i++]);
            }
        }
        return res.toString();
    }

    protected int getPercentCodeAt(int start, char[] chars) {
        if ((start < 0) || (start + 2) >= chars.length) {
            return -1;
        }
        String asString = new String(chars, start, 3);
        Matcher matcher = percentCodePattern.matcher(asString);
        if (matcher.find()){
            String codeAsHex = "0x" + chars[start + 1] + chars[start + 2];
            Integer codeAsInt = Integer.decode(codeAsHex);
            return codeAsInt;
        }
        return -1;
    }
}
