package misc;

import bioinf.elc.duplicates.Histo;

import java.util.Random;

public class IntDuplicates {

    static final int SIZE = 1_00_000_000;

    static int[] counters = new int[SIZE];

    public static void main(String[] args) {

        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            counters[random.nextInt(SIZE)]++;
        }

        Histo histo = new Histo();
        for (int i = 0; i < SIZE; i++) {
            histo.increment(counters[i]);
        }

        System.out.println(histo);

    }

}
