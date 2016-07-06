package elc.duplicates;

import java.util.ArrayList;
import java.util.List;

public class BruteForce implements Algorithms{

    private Reads reads;

    private Histo histo;

    public BruteForce(Reads reads, Histo histo) {
        this.reads = reads;
        this.histo = histo;
    }

    public Histo getHisto() {
        return histo;
    }

    // O(n*n)
    public void process() {

        final List<Read> readsList = reads.getReadsList();
        for (int i = 0; i < readsList.size(); ++i) {
            final Read lhs = readsList.get(i);
            if (lhs == null) continue;
            final List<Read> dupes = new ArrayList<>();

            for (int j = i + 1; j < readsList.size(); ++j) {
                final Read rhs = readsList.get(j);
                if (rhs == null) continue;

                if (isReadsMatch(lhs, rhs)) {
                    dupes.add(rhs);
                    readsList.set(j, null);
                }
            }

            if (!dupes.isEmpty()) {
                dupes.add(lhs);
                final int duplicateCount = dupes.size();
                histo.increment(duplicateCount);

            } else {
                histo.increment(1);
            }
        }

    }

    private boolean isReadsMatch(final Read lhs, final Read rhs) {

        final String lNucleotides = lhs.getNucleotides();
        final String rNucleotides = rhs.getNucleotides();
        final int minLength = Math.min(lhs.length(), rhs.length());
        int maxErrors = (int) Math.floor((minLength) * reads.getMAX_DIFF_RATE());
        int errors = 0;
        for (int i = 0; i < minLength; ++i) {
            if (lNucleotides.charAt(i) != rNucleotides.charAt(i) && ++errors > maxErrors) {
                return false;
            }
        }
        return true;
    }

}
