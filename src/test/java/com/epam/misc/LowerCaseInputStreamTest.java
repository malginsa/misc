package com.epam.misc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LowerCaseInputStreamTest {

    private InputStream lcis;

    @Before
    public void setUp() {
        try {
            lcis =
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("test.txt")));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readSingleChar() throws IOException {
        int i = lcis.read();
        assertEquals("single char test", (char)i, 'a');
    }

    @Test
    public void readArraysOfChar() throws IOException {
        byte[] arr = new byte[4];
        int realLen = lcis.read(arr, 1, 3);
        byte[] realArr = Arrays.copyOf(arr, realLen);
        for (int i = 0; i < realArr.length; i++) {
            System.out.print((char)realArr[i]);
        }
    }

}