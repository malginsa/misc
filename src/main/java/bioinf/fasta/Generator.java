package bioinf.fasta;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Generator {

    private static final int NUCLES_IN_LINE = 80;

    private static final char[] NUCLES = {'A', 'T', 'C', 'G', 'N'};

    private static Random random = new Random();

    private static String getRandomNucles(int count) {
        char[] chars = new char[count+1];
        for (int i = 0; i < count; i++) {
            chars[i] = NUCLES[random.nextInt(5)];
        }
        chars[count] = '\n';
        return new String(chars);
    }

    private static void generateOneReference(String fileName, int nuclesCount) {
        final Path file = Paths.get(fileName);
        Charset charset = Charset.forName("US-ASCII");
        int linesCount = nuclesCount / NUCLES_IN_LINE;
        int remainder = nuclesCount % NUCLES_IN_LINE;

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(">hugeRef\n");
            for (int i = 0; i < linesCount; i++) {
                writer.write(getRandomNucles(NUCLES_IN_LINE));
            }
            writer.write(getRandomNucles(remainder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateChromosomes(String fileName, long count, int medianSize, double deviation) {
        final Path file = Paths.get(fileName);
        Charset charset = Charset.forName("US-ASCII");

        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            for (int i = 0; i < count; i++) {
                String descr = "chrom"+i;
                // TODO checked int
                int nuclesCount = Math.min(medianSize + random.nextInt((int)(medianSize * deviation)),
                        Integer.MAX_VALUE);
                writeChromosome(writer, descr, nuclesCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeChromosome(BufferedWriter writer, String descr, int nuclesCount) {

        int linesCount = nuclesCount / NUCLES_IN_LINE;
        int remainder = nuclesCount % NUCLES_IN_LINE;
        try {
            writer.write('>' + descr + '\n');
            for (int i = 0; i < linesCount; i++) {
                writer.write(getRandomNucles(NUCLES_IN_LINE));
            }
            writer.write(getRandomNucles(remainder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        generateOneReference("/media/msa/small/biodata/hugeRef.fasta", Integer.MAX_VALUE / 2 + 1 );
//        generateOneReference("/media/msa/small/biodata/tiny.fasta", 81);
//        generateChromosomes("/media/msa/small/biodata/chromosomes.fasta", 1_000_000, 1_000, 0.8);
        generateChromosomes("/media/msa/small/biodata/pine.fasta", 3058792, 8_000, 0.9);
    }
}
