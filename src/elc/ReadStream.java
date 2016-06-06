package elc;

import util.Utils;

import java.util.Iterator;

/**
 * Created by msa on 05.06.16.
 */
public class ReadStream implements Iterable<DNARead>{

    private final int COUNT = 1_000_000;
    private final int readLength;

    public ReadStream(int readLength) {
        this.readLength = readLength;
    }

    @Override
    public Iterator<DNARead> iterator() {
        return new Iterator<DNARead>() {

            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < COUNT;
            }

            @Override
            public DNARead next() {
                current++;
                Utils.hardWorkSimulation(readLength);
                return new DNARead(readLength);
            }
        };
    }

}
