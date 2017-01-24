package com.epam.misc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LowerCaseInputStreamTest {

    InputStream lcis;

    @Before
    public void before() {
        try {
            lcis = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream("test.txt")));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            lcis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readSingleChar() throws IOException {
        int i = lcis.read();
        assertEquals("single char test", (char) i, 'a');
    }

    @Test
    public void readArraysOfChar() throws IOException {
        byte[] etalon = new byte[]{97, 98, 99, 100, 101, 102};
        byte[] arr = new byte[6];
        int realLen = lcis.read(arr, 0, 6);
        byte[] realArr = Arrays.copyOf(arr, realLen);
        assertArrayEquals("array of chars test", realArr, etalon);
    }
}
