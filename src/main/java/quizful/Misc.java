package quizful;

import java.util.*;

public class Misc {
    public static void main(String[] args) {
        List a = new ArrayList<>(); // 4
        System.out.println(a.getClass());
        a.add(1.5); // 5
        a.add("2r");
        System.out.println(a);
        System.out.println("" + a.get(0) + ' ' + a.get(1));

        char ch1 = '1';
        char ch3 = '\u0031';
        char ch2 = 49;
        System.out.println(ch1 + ch2 + ch3);

        Integer i1 = 1;
        Formatter f = new Formatter();
        f.format("%1$b ", i1.toString());
        System.out.println(f.toString());

        String s = "Java";
        System.out.println(s.concat(" rules!")); // Java rules
        System.out.println(s); // Java

        long l = 7;
        String st = new String();
        st = "" + l;
        System.out.println(st);
        s = Long.toString(l);
        System.out.println(s);

        Integer i = new Integer("7");
        inc(i);
        System.out.println(i); // 7

        System.out.println(i.toString() == i.toString()); // false
        System.out.println(i.toString().intern() == i.toString().intern()); // true

        System.out.println( 0.3 == 0.1d + 0.1d + 0.1d ); // false

        Integer i2 = 5000;
        System.out.println(i2.hashCode());

        System.out.println("" + 012 + " " + 0x12); // 10 18

        int []a1 = {5,5};
        int b1 = 1;
        a1[b1] = b1 = 0;
        System.out.println(Arrays.toString(a1));

        PrintBinaryRepresentation(7);
        PrintBinaryRepresentation(~7);

    }

    private static void PrintBinaryRepresentation(Integer i) {
        System.out.println(i + " in binary representation: " + Integer.toBinaryString(i));
    }

    private static void inc(Integer i) {
        i++;
    }
}
