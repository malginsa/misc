package elc.tape;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TapedRead extends Read {

    private Tape tape;
    private int startIndex;  // index of first nucleotide on Tape
    private int length;
    private NuclesDiffs diffs;

    public TapedRead(Tape tape, int startIndex, int length,  NuclesDiffs diffs) {
        super();
        this.tape = tape;
        this.startIndex = startIndex;
        this.length = length;
        this.diffs = diffs;
    }

    @Override
    public char getNucl(int index) {
        final Optional<Character> optional = diffs.getNucl(index);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return this.tape.getNucl(index);
        }
    }

    @Override
    public String getAllNucles() {
        final CharSequence charSequence = this.tape.getNucles(startIndex, length);
        // TODO: to optimize
        final StringBuilder stringBuilder = new StringBuilder(charSequence);

        return null;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                "TapedRead{" +
//                "tape=" + tape +
                "nucles=" + this.getAllNucles() +
                ", startIndex=" + startIndex +
                ", diffs=" + diffs +
                '}';
    }
}
