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

        Map<Read, List<Read>> candidates = this.findCandidates();
//        Map<Read, List<Read>> candidates = this.findCandidatesBruteForce();
//        Map<Read, List<Read>> candidates = this.allCandidates();

//        candidates = this.sortKeys(candidates);
//        this.checkKeysOrder(candidates);

        // TODO: really neccessary?
//        this.sortCandidates(candidates);

        this.checkOrderOfCandidates(candidates);

        createHisto(candidates);
    }


    private Map<Read,List<Read>> findCandidatesBruteForce() {

        Map<Read, List<Read>> candidatesTotal = new LinkedHashMap<>();

        final Read[] readsArray = this.reads.getReadsList().stream().toArray(size -> new Read[size]);
        for (int i = 0; i < readsArray.length - 1; i++) {
            for (int j = i + 1; j < readsArray.length; j++) {
                if (this.compareReadsByHashes(readsArray[i], readsArray[j])) {
                    this.addCandidate(candidatesTotal, readsArray[i], readsArray[j]);
                }
            }
        }
        return candidatesTotal;
    }


    private void addCandidate(Map<Read, List<Read>> candidatesTotal, Read read, Read candidate) {
        List<Read> candidates = candidatesTotal.get(read);
        if (null == candidates) {
            candidates = new ArrayList<>();
            candidatesTotal.put(read, candidates);
        }
        if (!candidates.contains(candidate)) {
            candidates.add(candidate);
        }
    }


    // return true if any band's hashes are equal
    private boolean compareReadsByHashes(Read read1, Read read2) {
        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {
            if (read1.getHash(band) == read2.getHash(band)) {
                return true;
            }
        }
        return false;
    }


    // key - read, value - it's candidates
    private Map<Read, List<Read>> findCandidates() {

        Map<Read, List<Read>> candidatesTotal = new LinkedHashMap<>();
//        Map<Read, List<Read>> candidatesTotal = new HashMap<>();

        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {

            // TODO: band >= read's band next_id
            final Map<Integer, List<Read>> readsByHash = groupReadsByHash(band);

            this.checkOrderOfGroup(readsByHash);

            // accumulate found candidates
            for ( List<Read> value : readsByHash.values()) {
                for (int i = 0; i < value.size() - 1; i++) {
                    this.addListOfCandidates(candidatesTotal, value, i);
                }

            }
        }
        return candidatesTotal;
    }


    // add candidates from list[first+1 .. size-1] to candidatesTotal, where key is list.get(first)
    private void addListOfCandidates(Map<Read, List<Read>> candidatesTotal,
                                     List<Read> list, int firstIndex) {
        final Read first = list.get(firstIndex);
        List<Read> candidates = candidatesTotal.get(first);
        if (null == candidates) {
            candidates = new ArrayList<>();
            candidatesTotal.put(first, candidates);
        }
        for (int i = firstIndex + 1; i < list.size(); i++) {
            final Read candidate = list.get(i);
            if (!candidates.contains(candidate)) {
                candidates.add(candidate);
            }
        }
    }


//    private void _addCandidate(Map<Read, List<Read>> candidatesTotal, Read read, Read candidate) {
//        List<Read> candidates = candidatesTotal.get(read);
//        if (null == candidates) {
//            candidates = new ArrayList<>();
//            candidatesTotal.put(read, candidates);
//        }
//        if (!candidates.contains(candidate)) {
//            candidates.add(candidate);
//        }
//    }


    // return candidates Map sorted by keys
    private Map<Read, List<Read>> sortKeys(Map<Read, List<Read>> candidates) {

        final Set<Read> keySet = candidates.keySet();
        final Read[] keys = keySet.stream().toArray(size -> new Read[size]);
        Arrays.sort(keys);
        Map<Read, List<Read>> result = new LinkedHashMap<>();
        for (int i = 0; i < keys.length; i++) {
            Read key = keys[i];
            final List<Read> value = candidates.get(key);
            result.put(key, value);
        }
        System.out.println("Map has sorted by keys");
        return result;

    }


    // sort each list, due to match results to ELC
    private void sortCandidates(Map<Read, List<Read>> candidates) {

//        this.checkSorted(candidates);
        for (List<Read> readList : candidates.values()) {
            readList.sort(new Comparator<Read>() {
                @Override
                public int compare(Read read1, Read read2) {
                    return Integer.compare(read1.getId(), read2.getId());
                }
            });
        }
        for (List<Read> readList : candidates.values()) {
            this.checkOrderOfList(readList, "  invoked from sortCandidates");
        }
    }


    private void checkOrderOfCandidates(Map<Read, List<Read>> candidates) {

        for (Map.Entry<Read, List<Read>> entry : candidates.entrySet()) {
            final List<Read> value = entry.getValue();
//            this.checkOrderOfList(value, "  invoked from checkOrderOfCandidates");
            final Read key = entry.getKey();
            final Read candidate1 = value.get(0);
            if (key.getId() >= candidate1.getId()) {
                System.out.println("checkOrderOfCandidates fault");
                return;
            }
        }
        System.out.println("checkOrderOfCandidates ok");
    }


    private void checkOrderOfList(List<Read> list, String message) {
        for (int i = 0; i < list.size() - 1; i++) {
             if (list.get(i).getId() >= list.get(i+1).getId()) {
                 System.out.println("list doesn't ordered: "
                         + list.get(i).getId() + " >= " + list.get(i+1).getId()
                         + message);
             }
        }
    }


    // each list must be ordered; and the first read in each group must be in increasing order
    private void checkOrderOfGroup(Map<Integer, List<Read>> candidates) {

        // check order of first reads
        List<Read> firsts = new ArrayList<>();
        for (List<Read> readList : candidates.values()) {
            firsts.add(readList.get(0));
        }
        this.checkOrderOfList(firsts, "  invoked from checkOrderOfGroup: check firsts");

        // check order of each list
        for (List<Read> readList : candidates.values()) {
            this.checkOrderOfList(readList, "  invoked from checkOrderOfGroup: check list of group");
        }
    }




    // groups reads depend of band's values
    // key - hash value of band, value - list of reads which hash's value equals key
    private Map<Integer, List<Read>> groupReadsByHash(int bandNumber) {

        Map<Integer, List<Read>> readsByHash = new LinkedHashMap<>();

        // TODO: parallel ? - NO order of reads have to be preserved
        for (int i = 0; i < reads.getReadsList().size(); i++) {
            final Read read = reads.getReadsList().get(i);
            final int hash = read.getHash(bandNumber);
            List<Read> readsList = readsByHash.get(hash);
            if (null == readsList) {
                readsList = new ArrayList<Read>();
                readsByHash.put(hash, readsList);
            }
            readsList.add(read);
        }

        return readsByHash;
    }


    // actually, a bit slowly than matchesBruteForce
    // TODO: to optimize
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


    // every read's candidates list consists of every other read
    public Map<Read, List<Read>> allCandidates() {
        Map<Read, List<Read>> candidatesTotal = new LinkedHashMap<>();
        for (Read read : reads.getReadsList()) {
            List<Read> list = new ArrayList<>();
            for (Read candidate : reads.getReadsList()) {
                if (!read.equals(candidate)) {
                    list.add(candidate);
                }
            }
            candidatesTotal.put(read, list);
        }
        return candidatesTotal;
    }


    private void createHisto(Map<Read, List<Read>> candidates) {
        List<Read> dupesList = new ArrayList<>();
        // dupes next_id among candidates
        int dupes;
        for (Read read : candidates.keySet()) {
            if (dupesList.contains(read)) {
                continue;
            }
            dupes = 0;
            for (Read candidate : candidates.get(read)) {
                if (!dupesList.contains(candidate) &&
                        matchesBruteForce(read, candidate, this.reads.getMAX_DIFF_RATE()))
                {
                    dupes++;
                    dupesList.add(candidate);
                }
            }
            if (dupes > 0) {
                // add read to dupes next_id
                histo.increment(dupes + 1);
                dupesList.add(read);
            }
        }
        final int sizeOfReads = this.reads.getReadsList().size();
        final int sizeOfUsed = dupesList.size();
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

        final Map<Read, List<Read>> candidates = kateAlgorithm.findCandidates();

        for (Map.Entry<Read, List<Read>> entry : candidates.entrySet() ) {
            System.out.println(entry.getKey() + " candidates: " + entry.getValue());
        }

        kateAlgorithm.process();

        System.out.println(kateAlgorithm.getHisto());

    }
}
