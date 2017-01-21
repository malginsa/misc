package com.epam.misc;

public class TenSteps {
    static int getCount(int steps){
        if (steps < 1) {
            return 0;
        }
        switch (steps) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            default:
                return calcCount(steps);
        }
    }

    private static int calcCount(int steps) {
        int counts[] = new int[steps];
        counts[steps-1] = 1;
        counts[steps-2] = 2;
        counts[steps-3] = 4;
        for (int i = steps-4; i >= 0; i--) {
            counts[i] = counts[i+1] + counts[i+2] + counts[i+3];
        }
        return counts[0];
    }

    public static void main(String[] args) {
        System.out.println(getCount(10));
    }
}
