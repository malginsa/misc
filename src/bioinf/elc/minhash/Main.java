package bioinf.elc.minhash;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        Data data = new Data();

        String str1 = "abcdefghijklmnopqrst";
        String str2 = "abcdsfghijklmnopqrru";

        System.out.println(str1 + "  " + data.calculateMinHash(str1, Objects::hashCode));
        System.out.println(str2 + "  " + data.calculateMinHash(str2, Objects::hashCode));
    }
}
