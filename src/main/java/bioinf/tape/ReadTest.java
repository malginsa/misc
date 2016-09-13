package bioinf.tape;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ReadTest {

    @Test
    public void sortTest() {
        StandaloneRead read1 = new StandaloneRead("C");
        StandaloneRead read2 = new StandaloneRead("T");
        StandaloneRead read3 = new StandaloneRead("A");
        List<StandaloneRead> list = new ArrayList<>();
        list.add(read2);
        list.add(read1);
        list.add(read3);
        System.out.println(list);

        Map<Integer, List<StandaloneRead>> map = new HashMap<>();
        map.put(0, list);

        for (List<StandaloneRead> readList : map.values()) {
            readList.sort(new Comparator<StandaloneRead>() {
                @Override
                public int compare(StandaloneRead read1, StandaloneRead read2) {
                    return Integer.compare(read1.getId(), read2.getId());
                }
            });
        }
        System.out.println(list);
    }
}