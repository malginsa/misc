package misc;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class CaesarsCodeTest {

    private CaesarsCode caesarsCode;

    @Before
    public void init() {
        caesarsCode = new CaesarsCode();
    }

    @Test
    public void getCharSimple() {
        char actual = caesarsCode.getChar(6);
        assertEquals('Ё', actual);
    }

    @Test
    public void getCharWithLooping() {
        char actual = caesarsCode.getChar(34);
        assertEquals('Б', actual);
    }

    @Test
    public void checkLenght() {
        assertEquals(33, CaesarsCode.ALPHABET.length());
    }

    @Test
    public void getIndex() {
        int actual = caesarsCode.getIndex('Й');
        assertEquals(10, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void getNoSuchElement() {
        caesarsCode.getIndex('-');
    }

    @Test
    public void decodeWithoutShift() {
        String actual = caesarsCode.decode("СЕ", 2);
        assertEquals("УЖ", actual);
    }

    @Test
    public void decodeWithShift() {
        String actual = caesarsCode.decode("ЭЯЮ", 1);
        assertEquals("ЮАЯ", actual);
    }
}