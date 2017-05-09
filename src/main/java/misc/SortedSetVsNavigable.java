package misc;

import java.util.*;

public class SortedSetVsNavigable {

    private static SortedSet<Integer> storage = new TreeSet<>();

    private static Integer getNextElem(Integer elem) {
        if (null == elem) {
            throw new IllegalArgumentException();
        }
        Iterator<Integer> iterator = storage.iterator();
        while (iterator.hasNext() && !iterator.next().equals(elem)) {
        }
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException();
        }
        return iterator.next();
    }

    private static Set<Integer> getPrevElems(Integer elem) {
        return storage.headSet(elem);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            storage.add(i);
        }

        System.out.println(getNextElem(-9));

    }
}
