package misc;

public class BitwiseOperations {

    public static void main(String[] args) {

        System.out.println(1<<31);
        System.out.println(1<<31-1);
        System.out.println(1<<31+1);

        System.out.println();
        int res = 0;
        for (int i = 0; i < 31; i++) {
            res <<= 1;
            res += 1;
            System.out.println(res);
        }

        System.out.println();

        res++;
        System.out.println(res);
        System.out.println();

        res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += 1;
        }
        System.out.println(res);


        System.out.println();
        System.out.println(-2>>1); // -1
        System.out.println(-2>>>1); // 2147483647

        System.out.println();
        System.out.println(2>>10); // 0
        System.out.println(-2>>10); // -1

        System.out.println(1|128); // 129
    }
}
