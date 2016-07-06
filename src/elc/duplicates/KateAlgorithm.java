package elc.duplicates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class KateAlgorithm implements Algorithms{

    private Reads reads;
    private Histo histo;

    public KateAlgorithm(Reads reads, Histo histo) {
        this.reads = reads;
        this.histo = histo;
        this.reads.calculateHashes();
    }

    public Histo getHisto() {
        return histo;
    }

    public void process() {
        final Map<Read, Set<Read>> candidates = this.findCandidates();
        // TODO: make it faster
        Set<Read> readIsDupe = new HashSet<>();
        // dupes count among candidates
        int dupes;
        for (Read read : candidates.keySet()) {
            if (readIsDupe.contains(read)) {
                continue;
            }
            dupes = 0;
            for (Read candidate : candidates.get(read)) {
                if (!readIsDupe.contains(candidate) &&
                        matchesBruteForce(read, candidate))
//                      matchesUsingHashes(read, candidate))
                {
                    dupes++;
                    readIsDupe.add(candidate);
                }
            }
            if (dupes > 0) {
                // add read to dupes count
                histo.increment(dupes + 1);
                readIsDupe.add(read);
            }
        }
        final int sizeOfReads = reads.getReadsList().size();
        final int sizeOfUsed = readIsDupe.size();
        // add uniq reads
        histo.increment(1, sizeOfReads - sizeOfUsed);
    }

    // key - read, value - it's candidates
    public Map<Read, Set<Read>> findCandidates() {

        Map<Read, Set<Read>> candidatesTotal = new HashMap<>();

        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {

            // TODO: band >= read's band count

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
                // TODO: remove(0)
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

    // actually, a bit slowly than matchesBruteForce
    public boolean matchesUsingHashes(Read left, Read right) {

        int errors = 0;
        // compare by hashes
        final int[] leftHashes = left.getHashes();
        final int[] rightHashes = right.getHashes();
        for (int i = 0; i < Math.min(rightHashes.length, leftHashes.length); i++) {
            if (leftHashes[i] != rightHashes[i]) {
                errors++;
            }
        }
        // now compare by nucleotides
        int index = 0;
        final char[] leftNucleotides = left.getNucleotides().toCharArray();
        final char[] rightNucleotides = right.getNucleotides().toCharArray();
        final int minLength = Math.min(left.length(), right.length());
        int maxErrors = (int) Math.floor(minLength * reads.getMAX_DIFF_RATE());
        while (index < minLength) {

            if (index % reads.getBAND_LENGTH() == 0) {
                final int bandIndex = index / reads.getBAND_LENGTH();
                if (leftHashes[bandIndex] != rightHashes[bandIndex]) {
                    errors--;
                }
            }
            if(leftNucleotides[index] != rightNucleotides[index]) {
                errors++;
            }
            if (errors > maxErrors) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean matchesBruteForce(Read left, Read right) {

        int errors = 0;
        final char[] leftNucleotides = left.getNucleotides().toCharArray();
        final char[] rightNucleotides = right.getNucleotides().toCharArray();
        final int minLength = Math.min(left.length(), right.length());
        int maxErrors = (int) Math.floor(minLength * reads.getMAX_DIFF_RATE());
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

    public static void main(String[] args) {

        List<Read> readsList = new ArrayList<>();
        readsList.add(new Read("AAAAAAA"));
        readsList.add(new Read("AAAAAAC"));
        readsList.add(new Read("CTCTCCC"));
        readsList.add(new Read("CCCCCCC"));
        readsList.add(new Read("CCCTCCC"));

        final double max_diff_rate = 0.4;
        Reads reads = new Reads(readsList, max_diff_rate);

        Histo histo = new Histo();
        KateAlgorithm kateAlgorithm = new KateAlgorithm(reads, histo);

        reads.printInfo();

        final Map<Read, Set<Read>> candidates = kateAlgorithm.findCandidates();

        for (Map.Entry<Read, Set<Read>> entry : candidates.entrySet() ) {
            System.out.println(entry.getKey() + " candidates: " + entry.getValue());
        }

        kateAlgorithm.process();

        System.out.println(kateAlgorithm.getHisto());

    }
}
