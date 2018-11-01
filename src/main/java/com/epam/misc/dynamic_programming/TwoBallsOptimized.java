package com.epam.misc.dynamic_programming;

public class TwoBallsOptimized extends TwoBalls{

    public int numberOfFloors(int balls, int floors) {
        if (floors <= 1) return 1;
        if (balls == 1) return floors - 1;
        if (floors <= balls) return floors - 1;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < floors + 1; i++) {
            int broken = numberOfFloors(balls - 1, i - 1);
            int notBroken = numberOfFloors(balls, floors - i);
            if (broken > notBroken) break;
            int candidate = Integer.max(broken, notBroken);
            if (min > candidate) min = candidate;
        }
        return min + 1;
    }

    private static void performanceMeasurement() {
        TwoBallsOptimized twoBalls = new TwoBallsOptimized();
        long start = System.nanoTime();
        twoBalls.numberOfFloors(2, 30);
        long stop = System.nanoTime();
        long elapsed = (stop - start) / 1_000_000;
        System.out.println("elapsed time: " + elapsed + " millis");
    }

    public static void main(String[] args) {

        performanceMeasurement();

    }
}
