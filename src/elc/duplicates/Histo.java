package elc.duplicates;

import java.util.HashMap;
import java.util.Map;

public class Histo {

    // key - bin, value - count items in bin
    private Map<Integer, Integer> gramma;

    public Histo() {
        gramma = new HashMap<>();
    }

    // Increments the value in the designated bin by 1.
    public void increment(int bin) {
        Integer count = gramma.get(bin);
        if (null == count) {
            count = 1;
        } else {
            count++;
        }
        gramma.put(bin, count);
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
        return gramma.toString();
    }

    public static void main(String[] args) {

        Histo histo = new Histo();
        histo.increment(1);
        histo.increment(1);
        histo.increment(3);
        histo.increment(5);

        System.out.println(histo);
    }
}
