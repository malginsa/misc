package bioinf.elc.duplicates;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public static void main(String[] args) {

        Map<Integer, String> lhm = new LinkedHashMap<>();
//        Map<Integer, String> lhm = new HashMap<>();

        lhm.put(5, "five");
        lhm.put(2, "two");
        lhm.put(7, "seven");
        lhm.put(1, "one");
        lhm.put(0, "zero");
        lhm.put(4, "four");
        lhm.put(6, "six");
        lhm.put(3, "three");
        lhm.put(5, "FIVE");

        System.out.println(lhm);

        for (Integer index : lhm.keySet()) {
            System.out.print(index + " ");
        }
        System.out.println();

        for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
            System.out.print(entry + " ");
        }
        System.out.println();
    }
}
