package elc.duplicates;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Read {

    private String nucleotides;

    private int[] hashes = null;

    public Read(String nucleotides) {
        this.nucleotides = nucleotides;
    }

    public String getNucleotides() {
        return nucleotides;
    }

    public int[] getHashes() {
        return hashes;
    }

    public int getHash(int BAND_INDEX) {
        // TODO: return 0 if read is shorter
        return this.hashes[BAND_INDEX];
    }

    public String hashesToString() {
        String res = "";
        for (int i = 0; i < hashes.length; i++) {
            res += hashes[i] + " ";
        }
        return res;
    }

    // hashes the read
    public void calculateHashes(int BANDS_LENGTH) {

        final int BANDS_COUNT = this.length() / BANDS_LENGTH;
        this.hashes = new int[BANDS_COUNT];
        char[] cread = this.nucleotides.toCharArray();
        for (int i = 0; i < BANDS_COUNT; i++) {
            final char[] chars = Arrays.copyOfRange(cread, i * BANDS_LENGTH, (i + 1) * BANDS_LENGTH);
            this.hashes[i] = new String(chars).hashCode();
        }
    }

    public int length() {
        return nucleotides.length();
    }

    @Override
    public String toString() {
        return this.nucleotides;
    }
}
