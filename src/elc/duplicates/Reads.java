package elc.duplicates;

import java.util.List;
import java.util.Optional;

public class Reads {

    private double MAX_DIFF_RATE;

    private int MAX_ERRORS;

    private int MAX_BANDS_COUNT;

    private int BAND_LENGTH;

    private List<Read> readsList = null;

    public Reads(List<Read> readsList, double maxDiffRate) {
        this.readsList = readsList;
        this.MAX_DIFF_RATE = maxDiffRate;
    }

    public double getMAX_DIFF_RATE() {
        return MAX_DIFF_RATE;
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

    public void calculateHashes() {
        final Optional<Integer> maxLength = this.readsList
                .stream()
//                .parallel()
                .map(r -> r.length())
                .max(Integer::compareTo);
        this.MAX_ERRORS = (int) Math.floor( maxLength.get() * this.MAX_DIFF_RATE );
        this.MAX_BANDS_COUNT = this.MAX_ERRORS + 1;
        this.BAND_LENGTH = maxLength.get() / this.MAX_BANDS_COUNT;
        readsList.stream().forEach(r -> r.calculateHashes(this.BAND_LENGTH));
    }

//    public void printWithHashes() {
//        readsList.stream()
//                .map(r -> r + " " + r.hashesToString())
//                .forEach(System.out::println);
//    }

    public void printInfo() {
        System.out.println("MAX_DIFF_RATE = " + this.getMAX_DIFF_RATE());
        System.out.println("MAX_ERRORS = " + this.getMAX_ERRORS());
        System.out.println("MAX_BANDS_COUNT = " + this.getMAX_BANDS_COUNT());
        System.out.println("BAND_LENGTH = " + this.getBAND_LENGTH());
        System.out.println();
        readsList.stream()
                .map(r -> r + "  " + r.hashesToString())
                .forEach(System.out::println);
        System.out.println();
    }

}
