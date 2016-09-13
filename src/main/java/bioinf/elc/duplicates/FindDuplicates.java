package bioinf.elc.duplicates;

import util.Utils;

import java.lang.reflect.Constructor;
import java.util.List;

public class FindDuplicates {

    public static Histo doFind(Reads reads, Class algorithms) throws Exception{

        Histo histo = new Histo();
        final Constructor constructor = algorithms.getDeclaredConstructor(Reads.class, Histo.class);
        final Algorithms instance = (Algorithms) constructor.newInstance(reads, histo);
        final double elapsed = Utils.timeMeasurement( () -> { instance.process(); });
        System.out.println();
        System.out.println(algorithms.getSimpleName());
        System.out.println(histo);
        System.out.println("elapsed: " + elapsed + " secs");
        return histo;
    }

    public static void main(String[] args) throws Exception {

        final String fileName = "/media/msa/small/elc_data/txt/CoverageBam_first_1_000_000_reads.txt";
//        final String fileName = "/media/msa/small/elc_data/txt/other_100_000_reads.txt";
        final double maxDiffRate = 0.03;
        final int readsLimit = 12_000;

        List<Read> readsList;
        Reads reads;

        readsList = FileReadsUtils.getLimited(fileName, readsLimit);
        reads = new Reads(readsList, maxDiffRate);
        Histo histo2 = doFind(reads, KateAlgorithm.class);

        readsList = FileReadsUtils.getLimited(fileName, readsLimit);
        reads = new Reads(readsList, maxDiffRate);
        Histo histo1 = doFind(reads, BruteForce.class);

        System.out.println("histos equality: " + histo1.equals(histo2));

    }
}
