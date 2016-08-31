package bioinf.tape;

import java.util.Optional;

public class TapedRead extends Read {

    private Tape tape;
    private int startIndex;  // index of first base on Tape
    private int length;
    private BasesDiffs diffs;

    public TapedRead(Tape tape, int startIndex, int length,  BasesDiffs diffs) {
        super();
        this.tape = tape;
        this.startIndex = startIndex;
        this.length = length;
        this.diffs = diffs;
    }

    @Override
    public char getBase(int index) {
        final Optional<Character> optional = diffs.getBase(index);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return this.tape.getBase(index);
        }
    }

    @Override
    public String getAllBases() {
        final CharSequence charSequence = this.tape.getBasesRange(startIndex, length);

//        Array.
//        charSequence.
        return charSequence.toString();
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                "taped {" +
//                "tape=" + tape +
                "bases=" + this.getAllBases() +
                ", startIndex=" + startIndex +
                ", diffs=" + diffs +
                '}';
    }
}
