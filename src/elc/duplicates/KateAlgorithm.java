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

        // value: Set<Read>
//        Map<Read, List<Read>> candidates = this.findCandidatesByBands();
        Map<Read, List<Read>> candidates = this.findCandidatesByReads();
//        Map<Read, List<Read>> candidates = this.findCandidatesBruteForce();
//        Map<Read, List<Read>> candidates = this.allCandidates();

        // TODO: really neccessary?
//        this.sortCandidates(candidates);

//        this.checkOrderOfCandidates(candidates);

        createHisto(candidates);
    }


    // key - read, value - it's candidates
    private Map<Read, List<Read>> findCandidatesByBands() {

        Map<Read, List<Read>> candidatesTotal = new LinkedHashMap<>();

        for(int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {
            final Map<Integer, List<Read>> readsByHash = groupReadsByBand(band);
            this.checkOrderOfGroup(readsByHash);
            // accumulate found candidates
            for ( List<Read> value : readsByHash.values()) {
                for (int i = 0; i < value.size(); i++) {
                    this.addGroupOfCandidates(candidatesTotal, value, i);
                }

            }
        }
        return candidatesTotal;
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


    private Map<Read, List<Read>> findCandidatesByReads() {

        Map<Read, List<Read>> candidatesTotal = new LinkedHashMap<>();
        // fill up keys-reads to preserve order of reads
        for (Read read : reads.getReadsList()) {
            candidatesTotal.put(read, null);
        }
        final Map<Integer, List<Read>> groups = groupsByReads();
        this.checkOrderOfGroup(groups);
        System.out.println("groups are filled up");
        for ( List<Read> group : groups.values()) {
            if (group.size() <= 1) {
                continue;
            }
            for (int readIndex = 0; readIndex < group.size() - 1; readIndex++) {
                this.addGroupOfCandidates(candidatesTotal, group, readIndex);
            }
        }
        return candidatesTotal;
    }


    private Map<Integer, List<Read>> groupsByReads() {

        Map<Integer, List<Read>> groupsByHash = new LinkedHashMap<>();
        for (Read read : reads.getReadsList()) {
            for (int band = 0; band < reads.getMAX_BANDS_COUNT(); band++) {
                final int hash = read.getHash(band);
                this.addReadToHashGroup(read, hash, groupsByHash);
            }
        }
        return groupsByHash;
    }


    private void addReadToHashGroup(Read read, int hash,
                                    Map<Integer, List<Read>> groupsByHash) {
        List<Read> readsList = groupsByHash.get(hash);
        if (null == readsList) {
            readsList = new ArrayList<Read>();
            groupsByHash.put(hash, readsList);
        }
        if (!readsList.contains(read)) {
            readsList.add(read);
        }
    }


    // add candidates from group[first+1 .. size-1] to candidatesTotal, where key is group.get(first)
    private void addGroupOfCandidates(Map<Read, List<Read>> candidatesTotal,
                                      List<Read> group, int keyIndex) {
        final Read key = group.get(keyIndex);
        List<Read> candidatesOfKey = candidatesTotal.get(key);
        if (null == candidatesOfKey) {
            candidatesOfKey = new ArrayList<>();
            candidatesTotal.put(key, candidatesOfKey);
        }
        for (int i = keyIndex + 1; i < group.size(); i++) {
            final Read read = group.get(i);
            if (read.equals(key)) {
                continue;
            }
            if (candidatesOfKey.contains(read)) {
                continue;
            }
            candidatesOfKey.add(read);
        }
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


    private void checkOrderOfCandidates(Map<Read, List<Read>> candidates) {

        // check order of keys
        final Read[] keys = candidates.keySet().toArray(new Read[0]);
        for (int i = 0; i < keys.length - 1; i++) {
            if (keys[i].getId() >= keys[i + 1].getId()) {
                System.out.println("keys order fault:  " + keys[i].getId() + " >= " + keys[i + 1].getId());
            }
        }

        // check order of every key and first read from value
        for (Map.Entry<Read, List<Read>> entry : candidates.entrySet()) {
            final List<Read> value = entry.getValue();
            if (null == value) {
                continue;
            }
            if (value.size() < 1) {
                System.out.println("WARNING:  empty listReads detected");
            }
            final Read first = value.get(0);
            if (entry.getKey().getId() >= first.getId()) {
                System.out.println("check Order Of Candidates fault  id: " + entry.getKey().getId());
            }
        }
        System.out.println("check Order Of Candidates ok");
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


    private void checkNotStrictOrderOfList(List<Read> list, String message) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getId() > list.get(i+1).getId()) {
                System.out.println("list doesn't ordered: "
                        + list.get(i).getId() + " >= " + list.get(i+1).getId()
                        + message);
            }
        }
    }


    // each list must be ordered; and the first read in each group must be not strictly ordered(duplicates allowed)
    private void checkOrderOfGroup(Map<Integer, List<Read>> groups) {

        // check order of first reads
        List<Read> firsts = new ArrayList<>();
        for (List<Read> readList : groups.values()) {
            firsts.add(readList.get(0));
        }
        this.checkNotStrictOrderOfList(firsts, "  checkOrderOfGroup: dupes in firsts detected");

        // check order of each list
        for (List<Read> readList : groups.values()) {
            this.checkOrderOfList(readList, "  checkOrderOfGroup: dupes in group detected");
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

        Set<Read> dupesList = new HashSet<>();
        int dupes;
        for (Map.Entry<Read, List<Read>> entry : candidates.entrySet()) {
            final Read key = entry.getKey();
            if (dupesList.contains(key)) {
                continue;
            }
            final List<Read> value = entry.getValue();
            if (null == value) {
                continue;
            }
            dupes = 0;
            for (Read candidate : value) {
                if (!dupesList.contains(candidate) &&
                        matchesBruteForce(key, candidate, this.reads.getMAX_DIFF_RATE()))
//                        matchesUsingHashes(key, candidate, this.reads.getMAX_DIFF_RATE()))
                {
                    dupes++;
                    dupesList.add(candidate);
                }
            }
            if (dupes > 0) {
                histo.increment(dupes + 1);
                dupesList.add(key);
            }
        }
        final int sizeOfReads = this.reads.getReadsList().size();
        final int sizeOfUsed = dupesList.size();
        // add uniq reads
        histo.increment(1, sizeOfReads - sizeOfUsed);
    }


    @Deprecated
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


    @Deprecated
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

        final Map<Read, List<Read>> candidates = kateAlgorithm.findCandidatesByBands();

        for (Map.Entry<Read, List<Read>> entry : candidates.entrySet() ) {
            System.out.println(entry.getKey() + " candidates: " + entry.getValue());
        }

        kateAlgorithm.process();

        System.out.println(kateAlgorithm.getHisto());

    }
}
