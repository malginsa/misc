package misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UnmodifiableCollection {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Collection<Integer> unmodifiableCollection = Collections.unmodifiableCollection(list);

//        unmodifiableCollection.add(4); // throws UnsupportedOperationException

    }

}
