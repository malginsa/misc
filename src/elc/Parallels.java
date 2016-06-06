package elc;

import util.Utils;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by msa on 05.06.16.
 */
public class Parallels {

    public static void main(String[] args) {

        int readLength = 500;

        ReadStream readStream = new ReadStream(readLength);

        int summ = 0;

        Instant start = Instant.now();

        for (DNARead dnaRead : readStream) {
            summ += dnaRead.summarization();
        }

        Instant stop = Instant.now();
        final Duration duration = Duration.between(start, stop);

        System.out.println("summ = " + summ);
        System.out.println("elapsed " + duration.toMillis() / 1_000. + " secs");

    }
}
