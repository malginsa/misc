package elc.duplicates;


import java.util.ArrayList;
import java.util.List;

public class BruteForce {

    // O(n*n)
    public static void accept2(String[] reads, double maxDiffRate, Histo histo) {

        for (int i = 0; i < reads.length; ++i) {
            final String lhs = reads[i];
            if (lhs == null) continue;
            final List<String> dupes = new ArrayList<>();

            for (int j = i + 1; j < reads.length; ++j) {
                final String rhs = reads[j];
                if (rhs == null) continue;

                if (isReadsMatch(lhs, rhs, maxDiffRate)) {
                    dupes.add(rhs);
                    reads[j] = null;
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

    private static boolean isReadsMatch(final String lhs, final String rhs, double maxDiffRate) {

        final int readLength = Math.min(lhs.length(), rhs.length());
        int maxErrors = (int) Math.floor((readLength) * maxDiffRate);
        int errors = 0;
        for (int i = 0; i < readLength; ++i) {
            if (lhs.charAt(i) != rhs.charAt(i) && ++errors > maxErrors) {
                return false;
            }
        }
        return true;
    }

}
