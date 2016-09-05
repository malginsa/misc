package misc;

import java.util.Arrays;

public class StringToByteArray {

    public static void main(String[] args) {

        String str = "Joy Of A Toy";
        final byte[] bytes = str.getBytes();
        System.out.println("length = " + bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
    }

}
