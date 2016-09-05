package misc;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static util.Utils.printSystemResourcesInfo;

public class MapPerformance {

    public static void main(String[] args) {
        printSystemResourcesInfo();
        final int stringCount = 60_000_000;
        final StringGenerator stringGenerator = new StringGenerator(13, stringCount);

        Instant start = Instant.now();
        Set<String> map = new HashSet<>(stringCount);

        int count = 0;
        while (stringGenerator.hasNext()) {
            map.add(stringGenerator.next());
            count++;
            if ((count % 1_000_000) == 0) {
                System.out.print(".");
            }
        }
        System.out.println();
        final Instant finish = Instant.now();
        final double elapsed = Duration.between(start, finish).toMillis() / 1000.;
        System.out.println("Elapsed time = " + elapsed + " sec");

        printSystemResourcesInfo();
    }
}
