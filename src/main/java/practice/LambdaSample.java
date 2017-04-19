package practice;

import java.util.stream.IntStream;

public class LambdaSample {

    public static void checkNumber(int i) throws Exception {
        if (i%2 == 0) {
            throw new Exception("i = " + i);
        }
    }

    public static void main(String[] args) {

        IntStream.range(0, 10).forEach(i -> {
            try {
                LambdaSample.checkNumber(i);
            } catch (Exception e) {
                System.out.println("got it: " + e.getMessage());
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LambdaSample.checkNumber(0);
                } catch (Exception e) {
                    System.out.println("got it in a new Thread");
                }
            }
        }).run();

    }
}
