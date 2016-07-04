package elc.duplicates;

import java.util.List;
import java.util.Optional;

public class Reads {

    private int MAX_ERRORS;

    private int MAX_BANDS_COUNT;

    private int BAND_LENGTH;

    private List<Read> readsList = null;

    // TODO: replace MAX_ERRORS with MAX_DIFF_RATE
    public Reads(List<Read> readsList, int MAX_ERRORS, boolean isHashesUsed) {
        this.readsList = readsList;
        this.MAX_ERRORS = MAX_ERRORS;
        if (isHashesUsed) {
            this.calculateHashes();
        }
    }

    public int getMAX_ERRORS() {
        return MAX_ERRORS;
    }

    public int getBAND_LENGTH() {
        return BAND_LENGTH;
    }

    public int getMAX_BANDS_COUNT() {
        return MAX_BANDS_COUNT;
    }

    public List<Read> getReadsList() {
        return readsList;
    }

    private void calculateHashes() {
        this.MAX_BANDS_COUNT = MAX_ERRORS + 1;
        final Optional<Integer> maxLength = this.readsList
                .stream()
                .map(r -> r.length())
                .max(Integer::compareTo);
        this.BAND_LENGTH = (int) Math.ceil((float) maxLength.get() / MAX_BANDS_COUNT);
        readsList.stream().forEach(r -> r.calculateHashes(BAND_LENGTH));
    }

    public void printWithHashes() {
        readsList.stream()
                .map(r -> r + " " + r.getHashesAsString())
                .forEach(System.out::println);
    }

//    private Integer[] getHashesAsString(int bandNumber) {
//        return readsList.stream()
//                .map(read -> read.getHash(bandNumber))
//                .toArray(size -> new Integer[size]);
//    }

}
