package elc;

import util.Utils;

import java.util.Arrays;

/**
 * Created by msa on 05.06.16.
 */
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
