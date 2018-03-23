package com.epam.misc;

import java.util.LinkedList;

public class AdditionToLinkedList {

    public static void main(String[] args) {

        LinkedList<String> strings = new LinkedList<>();

        strings.add("one");

        if (strings.size() < 2)
        {
            strings.addLast("three");
        }
        else
        {
            strings.add(2, "three");
        }

        System.out.println(strings);
    }
}
