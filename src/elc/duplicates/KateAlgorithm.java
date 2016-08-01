package elc.duplicates;

import java.util.*;

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
        Map<Read, Set<Read>> candidates = this.findCandidatesByBands();
        createHisto(candidates);
    }


    // key - read, value - it's candidates
    private Map<Read, Set<Read>> findCandidatesByBands() {

        Map<Read, Set<Read>> candidatesTotal = new LinkedHashMap<>();

        // fill up keys-reads to preserve order of candidates lists
        for (Read read : reads.getReadsList()) {
            candidatesTotal.put(read, null);
        }

        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {
            final Map<Integer, List<Read>> readsByHash = groupReadsByBand(band);
            // accumulate found candidates
            for ( List<Read> value : readsByHash.values()) {
                for (int i = 0; i < value.size(); i++) {
                    this.addGroupOfCandidates(candidatesTotal, value, i);
                }
            }
        }
        return candidatesTotal;
    }


    // groups reads depend of band's values
    // key - hash value of band, value - list of reads which hash's value equals key
    private Map<Integer, List<Read>> groupReadsByBand(int bandNumber) {

        Map<Integer, List<Read>> readsByHash = new LinkedHashMap<>();
        for (int i = 0; i < reads.getReadsList().size(); i++) {
            final Read read = reads.getReadsList().get(i);
            final int hash = read.getHash(bandNumber);
            this.addReadToHashGroup(read, hash, readsByHash);
        }
        return readsByHash;
    }


    private void addReadToHashGroup(Read read, int hash,
                                    Map<Integer, List<Read>> groupsByHash) {
        List<Read> readsList = groupsByHash.get(hash);
        if (null == readsList) {
            readsList = new ArrayList<>();
            groupsByHash.put(hash, readsList);
        }
        if (!readsList.contains(read)) {
            readsList.add(read);
        }
    }


    // add candidates from group[keyIndex+1 .. size] to candidatesTotal, where key is group.get(first)
    private void addGroupOfCandidates(Map<Read, Set<Read>> candidatesTotal,
                                      List<Read> group, int keyIndex) {
        final Read key = group.get(keyIndex);
        Set<Read> candidatesOfKey = candidatesTotal.get(key);
        if (null == candidatesOfKey) {
            candidatesOfKey = new HashSet<>();
            candidatesTotal.put(key, candidatesOfKey);
        }
        for (int i = keyIndex + 1; i < group.size(); i++) {
            final Read read = group.get(i);
            if (!read.equals(key)) {
                candidatesOfKey.add(read);
            }
        }
    }


    // actually, a bit slowly than matchesBruteForce
    // TODO: to optimize
    public boolean matchesUsingHashes(Read left, Read right, double maxDiffRate) {

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
        int maxErrors = (int) Math.floor(minLength * maxDiffRate);
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


    private void createHisto(Map<Read, Set<Read>> candidates) {

        Set<Read> dupes = new HashSet<>();
        int dupesCount;
        for (Map.Entry<Read, Set<Read>> entry : candidates.entrySet()) {
            final Read key = entry.getKey();
            if (dupes.contains(key)) {
                continue;
            }
            final Set<Read> value = entry.getValue();
            if (null == value) {
                continue;
            }
            dupesCount = 0;
            for (Read candidate : value) {
                if (!dupes.contains(candidate) &&
                        matchesBruteForce(key, candidate, this.reads.getMAX_DIFF_RATE()))
//                        matchesUsingHashes(key, candidate, this.reads.getMAX_DIFF_RATE()))
                {
                    dupesCount++;
                    dupes.add(candidate);
                }
            }
            if (dupesCount > 0) {
                histo.increment(dupesCount + 1);
                dupes.add(key);
            }
        }
        final int sizeOfReads = this.reads.getReadsList().size();
        final int sizeOfUsed = dupes.size();
        // add uniq reads
        histo.increment(1, sizeOfReads - sizeOfUsed);
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

        final Map<Read, Set<Read>> candidates = kateAlgorithm.findCandidatesByBands();

        for (Map.Entry<Read, Set<Read>> entry : candidates.entrySet() ) {
            System.out.println(entry.getKey() + " candidates: " + entry.getValue());
        }

        kateAlgorithm.process();

        System.out.println(kateAlgorithm.getHisto());

    }
}
