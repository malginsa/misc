package elc.tape;

// Store differences between nucleotides in tapedRead and tape

import java.util.*;

public class NuclesDiffs implements Iterator{

    // key - position on tape, value -- nucleotide
    private Map<Integer, Character> diffs;

    // empiric ratio to initialize primiry size of arrayList's
    private static int RATIO = 2;

    public NuclesDiffs() {
        diffs = new HashMap<>();
    }

    public void add(int index, Character nucl) {
        diffs.put(index, nucl);
    }

    public Optional<Character> getNucl(int index) {
        return Optional.ofNullable(diffs.get(index));
    }

    @Override
    public String toString() {
        return "diffs{" + diffs + '}';
    }

    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
