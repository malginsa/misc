package bioinf.elc.async_reading;

import util.Utils;

import java.util.Arrays;

public class DNARead {

    private byte[] data;

    public DNARead(int length) {
        data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = 1;
        }
    }

    public int summarization() {
        int res = 0;
        for (int i = 0; i < data.length; i++) {
            res += data[i];
        }
        Utils.hardWorkSimulation(data.length);
        return res;
    }

    @Override
    public String toString() {
        return "DNARead{" +
                "data=" + Arrays.toString(data) +
                ", length=" + data.length +
                '}';
    }
}
