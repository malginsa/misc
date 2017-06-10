package bioinf.elc.duplicates;

import java.util.Map;
import java.util.TreeMap;

public class Histo {

    // key - bin, value - items in bin
    private Map<Integer, Integer> gramma;

    public Histo() {
        gramma = new TreeMap<>();
    }

    // Increments the value in the designated bin by <count>.
    public void increment(int bin, int count) {
        Integer countInBin = gramma.get(bin);
        if (null == countInBin) {
            countInBin = count;
        } else {
            countInBin += count;
        }
        gramma.put(bin, countInBin);
    }

    // Increments the value in the designated bin by 1.
    public void increment(int bin) {
        this.increment(bin, 1);
    }

    // return total items of all items
    public int getTotal() {
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : gramma.entrySet()) {
            value += entry.getKey() * entry.getValue();
        }
        return value;
    }

    // return summary of all values except for bin is 0 or 1
    public int getDublicatesCount() {
        int res = 0;
        for (Integer integer : gramma.keySet()) {
            if (integer > 1) {
                res += gramma.get(integer);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return gramma.toString()
               + "  duplicates = " + this.getDublicatesCount()
                + "  total = " + this.getTotal();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Histo other = (Histo) obj;

        return gramma != null ? gramma.equals(other.gramma) : other.gramma == null;
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
