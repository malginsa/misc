package com.epam.misc;

import java.util.LinkedList;

public class AdditionToLinkedList {

    public static void main(String[] args) {

        LinkedList<String> strings = new LinkedList<>();

        strings.add("one");
        strings.add(1, "two");
        strings.add(2, "three");

        System.out.println(strings);
    }
}
