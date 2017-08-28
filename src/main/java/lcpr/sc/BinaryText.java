package lcpr.sc;

import java.util.Arrays;

public class BinaryText {

    private static final byte[] UNIX_LINE_DELIMITER = new byte[]{0x0A};
    private static final byte[] WINDOWS_LINE_DELIMITER = new byte[]{0x0D, 0x0A};

    private byte[] content = null;
    private byte[] lineDelimiter = null;
    private int marker = 0;

    public BinaryText(byte[] content) {
        this.content = content;
        lineDelimiter = detectLineDelimiter();
    }

    public byte[] getLineDelimiter() {
        return lineDelimiter;
    }

    protected int countMatches(byte[] fragment) {
        int result = 0;
        for (int i = 0; i < (content.length - fragment.length + 1); i++) {
            if (isSubArray(fragment, i)) {
                result++;
            }
        }
        return result;
    }

    private boolean isSubArray(byte[] fragment, int posOnContent) {
        if ((posOnContent + fragment.length) > content.length) {
            return false;
        }
        int byteMatches = 0;
        for (int j = 0; j < fragment.length; j++) {
            if (fragment[j] == content[posOnContent + j]) {
                byteMatches++;
            }
        }
        return byteMatches == fragment.length;
    }

    private byte[] detectLineDelimiter() {
        int unix = countMatches(UNIX_LINE_DELIMITER);
        int windows = countMatches(WINDOWS_LINE_DELIMITER);
        if(unix == windows) {
            return WINDOWS_LINE_DELIMITER;
        }
        if ((2 * windows) > 1) {
            return WINDOWS_LINE_DELIMITER;
        }
        if (unix > 1) {
            return UNIX_LINE_DELIMITER;
        }
        return null;
    }

    public boolean isText() {
        return (null != lineDelimiter);
    }

    public byte[] nextLine() {
        if (marker >= (content.length - 1)) {
            return null;
        }
        int endLine = marker;
        while ((!isSubArray(lineDelimiter, endLine))
                && (endLine < content.length)) {
            endLine++;
        }
        if (endLine == marker) {
            marker += lineDelimiter.length;
            return new byte[0];
        }
        byte[] result = Arrays.copyOfRange(content, marker, endLine);
        marker = endLine + lineDelimiter.length;
        return result;
    }
}
