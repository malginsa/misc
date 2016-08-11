package bioinf.elc.duplicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyOfTest {

    public static void main(String[] args) {

        List<Read> readsList = new ArrayList<>();
        readsList.add(new Read("A"));
        readsList.add(new Read("T"));
        readsList.add(new Read("C"));
        readsList.add(new Read("G"));

        System.out.println(readsList);

        final Read[] reads = readsList.toArray(new Read[0]);

        final Read[] copy = Arrays.copyOfRange(reads, 1, 3);
        Arrays.stream(copy).forEach(System.out::println);
    }
}
