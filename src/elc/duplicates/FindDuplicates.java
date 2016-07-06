package elc.duplicates;

import util.Utils;

import java.lang.reflect.Constructor;
import java.util.List;

public class FindDuplicates {

    public static void doFind(Reads reads, Class algorithms) throws Exception{

        Histo histo = new Histo();
        final Constructor constructor = algorithms.getDeclaredConstructor(Reads.class, Histo.class);
        final Algorithms instance = (Algorithms) constructor.newInstance(reads, histo);
        final double elapsed = Utils.timeMeasurement( () -> { instance.process(); });
        System.out.println();
        System.out.println(algorithms.getSimpleName());
        System.out.println(histo);
        System.out.println("elapsed: " + elapsed + " secs");

    }

    public static void main(String[] args) throws Exception {

//        final String fileName = "/media/msa/small/elc_data/txt/CoverageBam_first_1_000_000_nukleotides.txt";
        final String fileName = "/media/msa/small/elc_data/txt/other_100_000_reads.txt";
        final double maxDiffRate = 0.03;
        final int readsLimit = 50_000;

        List<Read> readsList = FileReadsUtils.getLimited(fileName, readsLimit);
        Reads reads = new Reads(readsList, maxDiffRate);
        doFind(reads, BruteForce.class);

        readsList = FileReadsUtils.getLimited(fileName, readsLimit);
        reads = new Reads(readsList, maxDiffRate);
        doFind(reads, KateAlgorithm.class);

    }
}
