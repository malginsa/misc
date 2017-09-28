package misc;

import java.util.Arrays;

public class CopyArrayOfChars {

    public static void main(String[] args) {

        char[] input = {'_', '_', '1', '2', '3'};
        int start = 2;
        int length = 3;

        char[] output = Arrays.copyOfRange(input, start, start + length + 1);
        output[length] = '!';
        String asString = new String(output);
        System.out.println("|" + asString + "|");
    }
}
