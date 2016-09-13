package misc;

import java.util.Iterator;
import java.util.Random;

public class StringGenerator implements Iterator {

    private int stringLength;
    private int count;
    private int generated;
    private Random rand;

    public StringGenerator(int stringLength, int count) {
        this.stringLength = stringLength;
        this.count = count;
        rand = new Random();
    }

    @Override
    public boolean hasNext() {
        return generated < count;
    }

    @Override
    public String next() {
        generated++;
        byte[] randBytes = new byte[stringLength];
        rand.nextBytes(randBytes);
        for (int i = 0; i < randBytes.length; i++) {
            randBytes[i] = (byte) (rand.nextInt(64) + 32);
        }
        return new String(randBytes);
    }

    public static void main(String[] args) {
        StringGenerator stringGenerator = new StringGenerator(50, 7);
        while (stringGenerator.hasNext()) {
            System.out.println(stringGenerator.next());
        }
    }
}
