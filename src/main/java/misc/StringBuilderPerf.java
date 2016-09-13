package misc;

import util.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringBuilderPerf {

    private static String createHugeString() {

        final StringBuilder builder = new StringBuilder();
        final StringGenerator generator = new StringGenerator(13, 58_000_000);

        int counter = 0;
        String previous = "";

        while(generator.hasNext()) {

            String next = generator.next();
            builder.append(next);

            counter++;
            if((counter % 1_000_000) == 0) {
                System.out.print("millions of strings = " + counter/1_000_000);
                System.out.print("  free = " + Utils.getFreeMemory());
                System.out.println("  used = " + Utils.getUsedMemorySize());
            }
            if (next.equals(previous)) {
                System.out.println("try to use another random generator");
            }
            previous = next;
        }

        System.out.println();
        Utils.printMemoryInfo();

        return builder.toString();
    }

    public static void main(String[] args) {
        Utils.printMemoryInfo();
        final double elapsed = Utils.timeMeasurement(() -> {

            final String hugeString = createHugeString();

            final Charset charset = Charset.forName("US-ASCII");
            final Path path = Paths.get("hugeString.txt");
            path.toFile().deleteOnExit();
            try (final BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
                writer.write(hugeString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Elapsed time = " + elapsed + " sec");
    }
}
