package elc.tape;

import java.util.*;

public class Tape {

    private final static int MAX_SHIFT = 5;

    public final static int ALLOWED_MISMATCHES = 1; // count of allowed mismatched nucleotides when read taped

    private static int[] SHIFTS = {0, 1, 2, -1, -2, 3, -3, 4, -4, 5, -5};

    // prefix of tape. It's crutch, used when second read shifted left more than one nucles
    private static String prefix = "....."; // must be '.' * MAX_SHIFT

    private StringBuilder nucleotides = null;

    private int count = 0; // count of taped reads

    public Tape(StandaloneRead firstRead) {
        nucleotides = new StringBuilder();
        nucleotides.append(prefix);
        nucleotides.append(firstRead.getAllNucles());
        this.count++;
    }

    // count of mismatched nucles, when read is compared with tale of tape
    // shift -- count of shifting nucles, > 0 -- shift to the right, < 0 -- shift to the left
    private int mismatchesCount(StandaloneRead read, int shift) {
        int count = 0;
        final int readLength = read.getLength();
        final int endIndex = shift > 0 ? readLength - shift : readLength;
        final int offset = this.nucleotides.length() - readLength + shift;
        // TODO: to optimize
        for (int i = 0; i < endIndex; i++) {
            if (read.getNucl(i) != this.nucleotides.charAt(offset + i)) {
                count++;
            }
        }
        return count;
    }

    private void extendTape(StandaloneRead read, int shift) {
        for (int i = 0; i < shift; i++) {
            nucleotides.append(read.getNucl(read.getLength() - shift + i));
        }
        this.count++;
    }

    private NuclesDiffs makeUpDiffs(StandaloneRead read, int startIndex) {
        NuclesDiffs diffs = new NuclesDiffs();
        for (int i = 0; i < read.getLength(); i++) {
            if (read.getNucl(i) != this.nucleotides.charAt(i + startIndex)) {
                diffs.add(i, read.getNucl(i));
            }
        }
        return diffs;
    }

    // trying to allocate read onto tape
    public Optional<TapedRead> toTape(StandaloneRead read) {
        for (int i = 0; i < SHIFTS.length; i++) {
            final int shift = SHIFTS[i];
            if (this.mismatchesCount(read, shift) <= this.ALLOWED_MISMATCHES) {
                if (shift > 0 ) {
                    this.extendTape(read, shift);
                }
                final int readLength = read.getLength();
                final int startIndex = this.nucleotides.length() - readLength;
                final TapedRead tapedRead = new TapedRead(this, startIndex, readLength,
                        this.makeUpDiffs(read, startIndex));
                return Optional.of(tapedRead);
            }
        }
        return Optional.empty();
    }

    public char getNucl(int index) {
        return this.nucleotides.charAt(index);
    }

    public CharSequence getNucles(int startIndex, int lenght) {
        return this.nucleotides.subSequence(startIndex, startIndex + lenght);
    }

    @Override
    public String toString() {
        return "Tape{" +
                "nucleotides=" + nucleotides +
                ", count=" + count +
                '}';
    }

    public static void main(String[] args) {

        final StandaloneRead firstRead = new StandaloneRead("AAAAT");
        final Tape tape = new Tape(firstRead);
        final StandaloneRead secondRead = new StandaloneRead("GAATC");
        final Optional<TapedRead> optional = tape.toTape(secondRead);

        System.out.println(tape);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("read " + secondRead + " isn't taped");
        }
    }
}
