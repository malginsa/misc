package elc.duplicates;

import java.util.Arrays;

public class MetaData {

    String read;
    byte[] nukleotidesHisto; // 0-A  1-T  2-C  3-G  4-N

    public MetaData(String read) {
        this.read = read;
        this.nukleotidesHistoCalculation();
    }

    private void nukleotidesHistoCalculation() {
        nukleotidesHisto = new byte[5];
        final char[] chars = read.toCharArray();
        for (int i = 0; i < chars.length; i++) {
//        ...
        }
    }

}
