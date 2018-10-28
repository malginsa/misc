package misc;

public class TwoBalls {

    public int numberOfFloors(int balls, int floors) {
        if (floors <= 1) return 1;
        if (balls == 1) return floors - 1;
        if (floors <= balls) return floors - 1;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < floors + 1; i++) {
            int candidate = Integer.max(
                    numberOfFloors(balls - 1, i - 1),
                    numberOfFloors(balls, floors - i)
            );
            if (min > candidate) min = candidate;
        }
        return min + 1;
    }
}
