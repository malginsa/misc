package com.epam.misc;

public class BracketsCheck {
    boolean doCheck(String s) {
        char[] chars = s.toCharArray();
        int nestings = 0;
        for (char ch : chars ) {
            switch (ch) {
                case '(':
                    nestings++;
                    break;
                case ')':
                    nestings--;
                    break;
            }
            if (nestings < 0) {
                return false;
            }
        }
        if (nestings != 0) {
            return false;
        }
        return true;
    }
}
