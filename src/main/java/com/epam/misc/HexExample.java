package com.epam.misc;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HexExample {

    public static void main(String[] args) throws DecoderException {

        // signed to unsigned
        byte b = (byte) 0x9C;
        int i = Byte.toUnsignedInt(b);
        char c = (char) i;

        i = 65600;
        c = (char) i;

        int[] codePoints = {156, 2049, 65540};
        String s = new String(codePoints, 0, 3);

        char[] chars = Hex.encodeHex(new byte[]{32, -100, (byte) 0x9C});
        System.out.println(new String(chars));

        byte[] bytes = Hex.decodeHex(chars);
        System.out.println(bytes.length);

        System.out.println(new String(new byte[]{48, 49, 50, 51, 52}));

    }
}
