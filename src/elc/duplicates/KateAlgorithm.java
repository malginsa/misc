package elc.duplicates;

import java.util.*;

public class KateAlgorithm {

    private Reads reads;

    public KateAlgorithm(Reads reads) {
        this.reads = reads;
    }

    // key - read, value - it's candidates
    public Map<Read, Set<Read>> findCandidates() {

        Map<Read, Set<Read>> candidatesTotal = new HashMap<>();

        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {

            // key - hash's value of band, value - list of reads which hash's value equals key
            Map<Integer, List<Read>> readsByHash = new HashMap<>();

            final int finalBand = band;

            // TODO: parallel ?
            reads.getReadsList().stream()
                    .forEach(read -> {
                        final int hash = read.getHash(finalBand);
                        List<Read> readsList = readsByHash.get(hash);
                        if (null == readsList) {
                            readsList = new ArrayList<Read>();
                            readsByHash.put(hash, readsList);
                        }
                        readsList.add(read);
                    });

            // accumulate found candidates
            for ( Map.Entry<Integer, List<Read>> candidate : readsByHash.entrySet()) {
                final List<Read> value = candidate.getValue();
                if (value.size() <= 1) {
                    continue;
                }
                final Read first = value.get(0);
                Set<Read> set = candidatesTotal.get(first);
                if (null == set) {
                    set = new HashSet<>();
                    candidatesTotal.put(first, set);
                }
                for (int i = 1; i < value.size(); i++) {
                    set.add(value.get(i));
                }
                candidate.setValue(null); // facilate GC
            }

        }

        return candidatesTotal;
    }

    public boolean matches(Read left, Read right) {

        int errors = 0;
        // compare by hashes
        final int[] leftHashes = left.getHashes();
        final int[] rightHashes = right.getHashes();
        for (int i = 0; i < Math.min(rightHashes.length, leftHashes.length); i++) {
            if (leftHashes[i] != rightHashes[i]) {
                errors++;
            }
        }
        // compare by nucleotides
        int index = 0;
        final char[] leftNucleotides = left.getNucleotides().toCharArray();
        final char[] rightNucleotides = right.getNucleotides().toCharArray();
        while (index < Math.min(right.length(), left.length())) {

            if (index % reads.getBAND_LENGTH() == 0) {
                final int bandIndex = index / reads.getBAND_LENGTH();
                if (leftHashes[bandIndex] != rightHashes[bandIndex]) {
                    errors--;
                }
            }
            if(leftNucleotides[index] != rightNucleotides[index]) {
                errors++;
            }
            if (errors > reads.getMAX_ERRORS()) {
                return false;
            }
            index++;
        }
        return true;
    }


    public static void main(String[] args) {

        List<Read> readsList = new ArrayList<>();
        readsList.add(new Read("AAAAAA"));
        readsList.add(new Read("AAAACC"));
        readsList.add(new Read("AACCCC"));
        readsList.add(new Read("CCCCCC"));

        Reads reads = new Reads(readsList, 2, true);

        reads.printWithHashes();
        System.out.println();

        KateAlgorithm kateAlgorithm = new KateAlgorithm(reads);

        final Map<Read, Set<Read>> candidates = kateAlgorithm.findCandidates();

        for (Map.Entry<Read, Set<Read>> entry : candidates.entrySet() ) {
            System.out.println(entry.getKey() + " candidates: " + entry.getValue());
        }


    }
}
