package com.epam.misc;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSample {

    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        m6();
//        m7();
//        m8();
//        m9();
//        m10();
        m11();
    }

    private static void m11() {
        String text = "  $#mdash;    $#mdash;$#mdash;";
        text = text.replaceAll("(\\s++)\\$#mdash;(\\s++)", "\\$#mdash;");
        System.out.println("|" + text + "|");
    }

    private static void m10() {
        String text = "1<ital>text.</ital> 2";
        text = text.replaceAll(".</ital>", "</ital>.");
        System.out.println(text);
    }

    private static void m9() {
        String text = "%HSection aV4.%H·281.348(...)";
        Pattern pattern = Pattern.compile("^%HSection [\\da-zA-Z]+.%H·([\\d\\.]+)\\D");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println("group 1: " + matcher.group(1));
        }
    }

    private static void m8() {
        String text = "1\n23";
        Pattern pattern = Pattern.compile("^([12345\n]+)$");// <centa>, <centd>
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println("matched");
        }
    }

    private static void m7() {
        String text = "<centa>(ii) \n$##65533;The first\n $##65533The second";
        Pattern pattern = Pattern.compile("^(<centa> ?)?+(\\(\\S+\\)){1}+([\\s\\S\\n]+)$");// <centa>, <centd>
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println("1st group: |" + matcher.group(1) + '|');
            System.out.println("2nd group: |" + matcher.group(2) + '|');
            System.out.println("3rd group: |" + matcher.group(3) + '|');
        }
    }

    private static void m6() {

        int multiline = Pattern.MULTILINE;

        System.out.println("multiline = " + multiline);

        String text = "<centa>(ii) \n$##65533;The first\n $##65533The second";

        String[] split = text.split("\n");

        text = split[0];

        Pattern pattern = Pattern.compile("^(<centa> ?)?+(\\(\\S+\\)){1}+(.+)$");// <centa>, <centd>
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            text = "";
            if (null != matcher.group(1)) {
                text = matcher.group(1);
            }
            text += matcher.group(2) + "$##ensp;" + matcher.group(3).trim();
        }

        StringBuilder builder = new StringBuilder();
        builder.append(text);
        for (int i = 1; i < split.length; i++) {
            builder.append('\n');
            builder.append(split[i]);
        }
        text = builder.toString();
        System.out.println('|' + text + '|');
    }

    private static void m5() {
        Pattern pattern = Pattern.compile("\\[");
        Matcher matcher = pattern.matcher("-[--");
        if (matcher.find()) {
            System.out.println("matched");
        }
        System.out.println("-[--".replaceAll("\\[", "+"));
    }

    private static void m4() {
//        String text = "<centa> (b1).rest ";
        String text = "<centd> (b1).rest ";
//        String text = "(b1).rest ";
        Pattern pattern = Pattern.compile("(<cent[ad]> )?(\\(\\S+\\))(.*)");// <centa>, <centd>
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String result = "";
            if (null != matcher.group(1)) {
                result = matcher.group(1);
            }
            result += matcher.group(2);
            result += matcher.group(3);
            System.out.println('|' + result + '|');
        }
    }

    private static void m3() {
        String text = "(b) The chairman of the commission shall ($65,000.00) per dollars ($62,500.00) per annum.\n";
        Pattern pattern = Pattern.compile("^(\\(\\S+\\))(.*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            System.out.println("|" + matcher.group(1) + "|");
            System.out.println("|" + matcher.group(2) + "|");
        }
    }

    private static void m2() {
        Pattern pattern = Pattern.compile("^Section (\\d+)\\.(.*)");
        Matcher matcher = pattern.matcher("Section 1.  Section 201 of the act of August 5, 1941 (P.L.752, No.286), known as the Civil Service Act, amended October 5, 2011 (P.L.310, No.76), is amended to read:");
        if (matcher.find()) {
            System.out.println("|" + matcher.group(1) + "|");
            System.out.println("|" + matcher.group(2) + "|");
        }
    }

    private static void m1() {
        Pattern pattern = Pattern.compile("^(¢a)?\\[?(SUB)?CHAPTER.*");

        System.out.println(pattern.matcher("_CHAPTER").matches()); // false
        System.out.println(pattern.matcher("_[SUBCHAPTER").matches()); // false

        System.out.println(pattern.matcher("CHAPTER").matches()); // true
        System.out.println(pattern.matcher("[CHAPTER_").matches()); // true
        System.out.println(pattern.matcher("¢aCHAPTER").matches()); // true

        System.out.println(pattern.matcher("SUBCHAPTER").matches()); // true
        System.out.println(pattern.matcher("[SUBCHAPTER").matches()); // true
        System.out.println(pattern.matcher("¢aSUBCHAPTER _").matches()); // true

        Pattern compile = Pattern.compile("^[ \\*]+$");
        if (compile.matcher("  * * *").find()) {
            System.out.println("matched");
        }
        if (compile.matcher(" 1 * * *").find()) {
            System.out.println("doesn't matched");
        }
    }
}
