package com.epam.misc;

public class Unicode0000 {

    public static void main(String[] args) {

        String s = "";
        char c = '\u0000';
        s += c;
        c = '\u0037';
        s += c;
        char[] chars = s.toCharArray();
        System.out.println(s);
    }
}
