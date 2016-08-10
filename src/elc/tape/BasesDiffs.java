package elc.tape;

// Store differences between bases in tapedRead and tape

import java.util.*;

public class BasesDiffs implements Iterator{

    // key - position on tape, value -- base
    private Map<Integer, Character> diffs;

    // empiric ratio to initialize primiry size of arrayList's
    private static int RATIO = 2;

    public BasesDiffs() {
        diffs = new HashMap<>();
    }

    public void add(int index, Character base) {
        diffs.put(index, base);
    }

    public Optional<Character> getBase(int index) {
        return Optional.ofNullable(diffs.get(index));
    }

    @Override
    public String toString() {
        return "diffs{ " + diffs + " }";
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
