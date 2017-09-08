package com.epam.misc;

import java.util.regex.Pattern;

public class RegexSample {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("^(¢a)?\\[?(SUB)?CHAPTER.*");

        System.out.println(pattern.matcher("_CHAPTER").matches()); // false
        System.out.println(pattern.matcher("_[SUBCHAPTER").matches()); // false

        System.out.println(pattern.matcher("CHAPTER").matches()); // true
        System.out.println(pattern.matcher("[CHAPTER_").matches()); // true
        System.out.println(pattern.matcher("¢aCHAPTER").matches()); // true

        System.out.println(pattern.matcher("SUBCHAPTER").matches()); // true
        System.out.println(pattern.matcher("[SUBCHAPTER").matches()); // true
        System.out.println(pattern.matcher("¢aSUBCHAPTER _").matches()); // true
    }
}
