package misc;

public class BithdaysCoincidence {

    public static void main(String[] args) {
        getProbability(42);
    }

    private static void getProbability(int personsNumber) {
        Double probability = 1.;
        for (int i = 0; i < personsNumber; i++) {
            probability = probability * (365 - i) / 365;
        }
        System.out.println(probability);
        System.out.println(1 - probability);
    }

}
