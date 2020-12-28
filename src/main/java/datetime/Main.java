package datetime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());
        System.out.println(incrementDateTime(localDateTime, minutes));
    }

    protected static String incrementDateTime(LocalDateTime localDateTime, int minutes) {
        LocalDateTime increased = localDateTime.plusMinutes(minutes);
        return "" + increased.getYear() + " " + increased.getDayOfYear() + " " + increased.toLocalTime();
    }

    protected static long getDiffInFullHours(LocalDateTime first, LocalDateTime second) {
        return ChronoUnit.HOURS.between(first, second);
    }
}
