package java8_my_samples.lesson160504;

import java.util.Optional;
import java.util.Random;

public class Optional_sample1 {

    private static Optional<Double> m1() {
        Random random = new Random();
        return random.nextBoolean() ? Optional.of(random.nextDouble()) : Optional.empty();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(" "+ i +" "+ m1().filter(t -> t>0.7).orElse(0d));
        }
    }
}
