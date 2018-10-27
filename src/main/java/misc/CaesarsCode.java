package misc;

import java.util.NoSuchElementException;

public class CaesarsCode {

    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String DECODED = "ЮОСЯЫБЫЭ";

    protected char getChar(int index) {
        int normalized = index % ALPHABET.length();
        return ALPHABET.charAt(normalized);
    }

    protected int getIndex(char ch) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            if (ALPHABET.charAt(i) == ch) return i;
        }
        throw new NoSuchElementException("Character " + ch + " isn't in ALPHABET");
    }

    public String decode(String string, int shift) {
        char[] result = new char[string.length()];
        for (int i = 0; i < result.length; i++) {
            char charAt = string.charAt(i);
            int index = getIndex(charAt);
            int newIndex = index + shift;
            result[i] = getChar(newIndex);
        }
        return new String(result);
    }

    public static void main(String[] args) {

        CaesarsCode caesarsCode = new CaesarsCode();
        for (int i = 0; i < ALPHABET.length(); i++) {
            System.out.println("shift=" + i + ' ' + caesarsCode.decode(DECODED, i));
        }
    }
}
