package elc.duplicates;

public interface Algorithms {

    void process();

    default boolean matchesBruteForce(Read left, Read right, double maxDiffRate) {

        int errors = 0;
        final char[] leftNucleotides = left.getNucleotides().toCharArray();
        final char[] rightNucleotides = right.getNucleotides().toCharArray();
        final int minLength = Math.min(left.length(), right.length());
        int maxErrors = (int) Math.floor(minLength * maxDiffRate);
        for (int index = 0; index < minLength; index++) {
            if(leftNucleotides[index] != rightNucleotides[index]) {
                errors++;
            }
            if (errors > maxErrors) {
                return false;
            }
        }
        return true;
    }
}
