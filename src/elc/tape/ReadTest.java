package elc.tape;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReadTest {

    @Test
    public void getHash() throws Exception {

        Read read1 = new Read("AAAG");
        read1.calculateHashes(3);

        Read read2 = new Read("AAAG");
        read2.calculateHashes(3);

        assertEquals(read1.getHash(0), read2.getHash(0));

        Read read3 = new Read("AAAGG");
        read3.calculateHashes(3);
        assertEquals(read1.getHash(0), read3.getHash(0));
        assertNotEquals(read1.getHash(1), read3.getHash(1));

        Read read4 = new Read("AAAGGG");
        read4.calculateHashes(3);
        assertEquals(read1.getHash(0), read4.getHash(0));
        assertNotEquals(read1.getHash(1), read3.getHash(1));
    }

    @Test
    public void sortTest() {
        Read read1 = new Read("C");
        Read read2 = new Read("T");
        Read read3 = new Read("A");
        List<Read> list = new ArrayList<>();
        list.add(read2);
        list.add(read1);
        list.add(read3);
        System.out.println(list);

        Map<Integer, List<Read>> map = new HashMap<>();
        map.put(0, list);

        for (List<Read> readList : map.values()) {
            readList.sort(new Comparator<Read>() {
                @Override
                public int compare(Read read1, Read read2) {
                    return Integer.compare(read1.getId(), read2.getId());
                }
            });
        }
        System.out.println(list);
    }
}