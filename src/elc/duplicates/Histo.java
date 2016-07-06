package elc.duplicates;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Histo {

    // key - bin, value - count items in bin
    private Map<Integer, Integer> gramma;

    public Histo() {
        gramma = new TreeMap<>();
    }

    // Increments the value in the designated bin by 1.
    public void increment(int bin, int count) {
        Integer countInBin = gramma.get(bin);
        if (null == countInBin) {
            countInBin = count;
        } else {
            countInBin += count;
        }
        gramma.put(bin, countInBin);
    }

    public void increment(int bin) {
        this.increment(bin, 1);
    }

    // return total count of all items
    public int getTotal() {
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : gramma.entrySet()) {
            value += entry.getKey() * entry.getValue();
        }
        return value;
    }

    @Override
    public String toString() {
        return gramma.toString()
                + "  total = "
                + this.getTotal();
    }

    public static void main(String[] args) {

        Histo histo = new Histo();
        histo.increment(1);
        histo.increment(1);
        histo.increment(3);
        histo.increment(5);
        histo.increment(3, 2);

        System.out.println(histo);
    }
}
