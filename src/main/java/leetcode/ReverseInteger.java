package leetcode;

public class ReverseInteger {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        long result = 0;
        do {
            int remainder = x % 10;
            int quotient = x / 10;
            result = result * 10 + remainder;
            x = quotient;
        } while (x != 0);
        if (result<Integer.MIN_VALUE || result > Integer.MAX_VALUE) return 0;
        return (int)result;
    }
}
