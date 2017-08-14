package challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Prime numbers generations
 */
public class PrimeNumbers {

    /**
     * Return prime numbers from 1 to "count"
     * Simple method of Eratosfen
     * @param count max prime number
     * @return List of prime numbers
     */
    private static List<Integer> simple(int count) {

        boolean[] flags = new boolean[count];
        for (int i = 0; i < flags.length; i++) {
            flags[i] = true;
        }

        int max = new Double(Math.sqrt(count)).intValue() + 1;

        for (int p = 2; p < max; p++) {
                int mult = 2;
                int i;
                while ((i = p * mult) < count) {
                    flags[i] = false;
                    mult++;
                }
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < flags.length; i++) {
            if(flags[i]) {
                res.add(i);
            }

        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(simple(100));
    }
}
