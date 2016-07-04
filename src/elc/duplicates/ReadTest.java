package elc.duplicates;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void findDplicatesTest() {

    }

}