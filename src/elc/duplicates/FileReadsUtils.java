package elc.duplicates;

import util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileReadsUtils {

    // Ugly implementation of statistic variables, calculated in a sequential stream
    private static int countLines;
    private static int maxLength;
    private static int minLength;


    // get only reads of max Length
    public static String[] getOnlyMaxLengthReads (String fileName) {

        final String[] arrayOfReads = getReadsStream(fileName)
                .toArray(size -> new String[size]);

        final Optional<Integer> maxLength = Arrays.stream(arrayOfReads)
                .map(s -> s.length())
                .max(Integer::compareTo);

        return Arrays.stream(arrayOfReads)
                .filter(s -> s.length() == maxLength.get())
                .toArray(size -> new String[size]);
    }


    // get stream of reads from file fileName
    public static Stream<String> getReadsStream (String fileName) {
        try {
            return Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // print some statistics from file fileName
    public static void printInfoOfFile(String fileName) {

        long distinct = getReadsStream(fileName).distinct().count();

        Stream<String> lines = getReadsStream(fileName);

        countLines = 0;
        minLength = Integer.MAX_VALUE;
        maxLength = 0;

        lines.sequential()
                .forEach(str -> {
                    countLines++;
                    final int length = str.length();
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    if (length < minLength) {
                        minLength = length;
                    }
                });

        System.out.println(fileName);
        System.out.println("    lines count: " + countLines);
        System.out.println("    distinct lines: " + distinct);
        System.out.println("    max length: " + maxLength);
        System.out.println("    min length: " + minLength);

    }

    // Save each read in separate file
    public static void saveReads(Stream<String> streamReads) {

        String prefix = "/media/msa/small/elc_data/separates/";
        String suffix = ".read.txt";
        int countFiles = 10_000;

        final String[] strings = streamReads.limit(countFiles).toArray(size -> new String[size]);

        List<String> list = new LinkedList<>();

        // TODO: refactor to use a stream
        for (int i = 0; i < countFiles; i++) {
            String name = prefix + i + suffix;
            list.clear();
            list.add(strings[i]);
            try {
                Files.write(Paths.get(name), list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    // print Info of each file in a folder
    public static void printInfoOfFolder () throws IOException {

        Files.walk(Paths.get("/media/msa/small/elc_data/txt/"))
                .filter(Files::isRegularFile)
                .filter(Files::isReadable)
                .map(Path::toString)
                .forEach(FileReadsUtils::printInfoOfFile);

    }


    public static void main(String[] args) throws IOException {

        System.out.println("elapsed: " + Utils.timeMeasurement(() -> saveReads(getReadsStream(
                "/media/msa/small/elc_data/txt/small1_first_1_000_000_nukleotides.txt"))));

    }
}
