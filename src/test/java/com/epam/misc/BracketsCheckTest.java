package com.epam.misc;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BracketsCheckTest {

    BracketsCheck bc;

    @Before public void initialize() {
        bc = new BracketsCheck();
    }

    @Test public void emptyString() {
        assertTrue("Empty string check", bc.doCheck(""));
    }
    @Test public void oneBracket() {
        assertFalse("string: \")\"", bc.doCheck(")"));
        assertFalse("string: \"(\"", bc.doCheck("("));
    }
    @Test public void nonBracket() {
        assertFalse("nonBracketSymbol", bc.doCheck("(&"));
        assertTrue("nonBracketSymbol", bc.doCheck("(&)"));
    }
    @Test public void misc1() {
        assertFalse("misc1.1", bc.doCheck("(()"));
        assertFalse("misc1.2", bc.doCheck("(()))"));
    }
    @Test public void misc2() {
        assertTrue("misc2.1", bc.doCheck("(())"));
        assertTrue("misc2.2", bc.doCheck("(())()"));
    }
    @Test public void misc3() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream("()".getBytes());
        System.setIn(bais);
        Scanner io = new Scanner(System.in);
        String str = io.next();
        io.close();
        assertTrue("misc3", bc.doCheck(str));
        System.setIn(System.in);
        bais.close();
    }
}
