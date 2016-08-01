package elc.tape;

public class TapedRead extends Read {

    private Tape tape;
    private int start;  // index of first nucleotide on Tape
    private int[] diffIndex;
    private Character[] diffNucl;

    public TapedRead() {
        super();
    }
}
