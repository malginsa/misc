package bioinf.tape;

import java.util.*;

public class Tape {

    private final static int MAX_SHIFT = 5;

    public final static int ALLOWED_MISMATCHES = 1; // count of allowed mismatched bases when read taped

    private static int[] SHIFTS = {0, 1, 2, -1, -2, 3, -3, 4, -4, 5, -5};

    // prefix of tape. It's crutch, used when second read shifted left more than one bases
    private static String prefix = "....."; // must be '.' * MAX_SHIFT

//    private StringBuilder bases = null;

    private byte[] bases;

    private int length;

    private int count = 0; // count of taped reads

    public Tape(StandaloneRead firstRead) {
//        bases = new StringBuilder();
        // TODO: try to optimize
        bases = new byte[Integer.MAX_VALUE];
//        bases.append(prefix);
//        bases.append(firstRead.getAllBases());
        this.count++;
    }

    // count of mismatched bases, when read is compared with tale of tape
    // shift -- count of shifting bases, > 0 -- shift to the right, < 0 -- shift to the left
    private int mismatchesCount(StandaloneRead read, int shift) {
        int count = 0;
        final int readLength = read.getLength();
        final int endIndex = shift > 0 ? readLength - shift : readLength;
        final int offset = this.length - readLength + shift;
        // TODO: to optimize
        for (int i = 0; i < endIndex; i++) {
            if (read.getBase(i) != this.bases[offset + i]) {
                count++;
            }
        }
        return count;
    }

    private void extendTape(StandaloneRead read, int shift) {
        for (int i = 0; i < shift; i++) {
//            bases.append(read.getBase(read.getLength() - shift + i));
        }
        this.count++;
    }

    private BasesDiffs makeUpDiffs(StandaloneRead read, int startIndex) {
        BasesDiffs diffs = new BasesDiffs();
        for (int i = 0; i < read.getLength(); i++) {
            if (read.getBase(i) != this.bases[i + startIndex]) {
                diffs.add(i, read.getBase(i));
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
                final int startIndex = this.length - readLength;
                final TapedRead tapedRead = new TapedRead(this, startIndex, readLength,
                        this.makeUpDiffs(read, startIndex));
                return Optional.of(tapedRead);
            }
        }
        return Optional.empty();
    }

    public char getBase(int index) {
        return (char) this.bases[index];
    }

    public CharSequence getBasesRange(int startIndex, int lenght) {
        // TODO
//        return this.bases.subSequence(startIndex, startIndex + lenght);
        return null;
    }

    @Override
    public String toString() {
        return "Tape{" +
                "bases=" + bases +
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
