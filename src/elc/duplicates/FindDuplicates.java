package elc.duplicates;

import util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class FindDuplicates {

    public static double MAX_DIFF_RATE = 0.03;
    public static Integer MAX_READS = 100;


    public static void doHisto(String[] reads, Class algorithms) {

        final String[] copyOf = Arrays.copyOf(reads, reads.length);
        Histo histo = new Histo();
        final Method acceptMethod;
        try {
            acceptMethod = algorithms.getMethod("accept");
            final double elapsed = Utils.timeMeasurement(
                    () -> {
                        try {
                            acceptMethod.invoke(copyOf, MAX_DIFF_RATE, histo);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println(histo);
            System.out.println("elapsed: " + elapsed + " secs");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

//        final String fileName = "/media/msa/small/elc_data/txt/CoverageBam_first_1_000_000_nukleotides.txt";

        final String fileName = "/media/msa/small/elc_data/txt/other_nukleotides.txt";
        final String[] allReads = FileReadsUtils.getOnlyMaxLengthReads(fileName);
        // Crop the array of reads by MAX_READS
        final String[] cropped = Arrays.stream(allReads)
                .limit(MAX_READS)
                .toArray(size -> new String[size]);

        doHisto(cropped, BruteForce.class);

    }
}
