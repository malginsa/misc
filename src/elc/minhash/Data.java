package elc.minhash;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Data {

    public static int SHINGLE_SIZE = 5;

    public static int READ_LENGTH = 20;

    // how many nukleotides shifted during k-shigles creation process
    public static int SHIFT = SHINGLE_SIZE;

    public static String[] datasource = {
            "AAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAC",
            "AAAAAAAAAAAAAAAAAACC",
            "AAAAAAAAAAAAAAAAAATT",
            "AAAAAAAACCCCCCCCCCCC",
            "CCCCCCCCCCCCCCCCCCCC"
    };

    /*
        Return all shingles of input.
     */
    public Collection<String> createShingles(String input) {

        final List<String> res = new LinkedList<>();
        int indexStart = 0;
        int indexStop = SHINGLE_SIZE;
        while(indexStop <= READ_LENGTH) {
            String shingle = input.substring(indexStart, indexStop);
            res.add(shingle);
            indexStart += SHIFT;
            indexStop += SHIFT;
        }
        return res;
    }

    /*
        Calculate hash of every shingle and return minimum of them
     */
    public Optional<Integer> calculateMinHash(Collection<String> shingles,
                                              Function<String, Integer> hashFunction) {
        return shingles.stream()
                .map(shingle -> hashFunction.apply(shingle))
                .min(Integer::compareTo);
    }

    /*
        Calculate hash of every shingle of string and return minimum of them
     */
    public Optional<Integer> calculateMinHash(String string,
                                              Function<String, Integer> hashFunction) {
        return this.calculateMinHash(this.createShingles(string), hashFunction);
    }

}
