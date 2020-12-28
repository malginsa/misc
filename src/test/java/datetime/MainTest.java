package datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class MainTest {

    @Test
    public void getDiffInFullHoursWithDayShiftTest() {
        LocalDateTime first = LocalDateTime.parse("2017-06-15T01:50");
        LocalDateTime second = LocalDateTime.parse("2017-06-16T01:10");
        long actual = Main.getDiffInFullHours(first, second);
        Assert.assertEquals(23, actual);
    }

    @Test
    public void getDiffInFullHoursWithoutDayShiftTest() {
        LocalDateTime first = LocalDateTime.parse("2017-06-15T01:50");
        LocalDateTime second = LocalDateTime.parse("2017-06-15T02:50");
        long actual = Main.getDiffInFullHours(first, second);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void incrementDateTimeTest1() {
        LocalDateTime input = LocalDateTime.parse("2017-12-31T22:30:15");
        int minutes = 200000;
        String actual = Main.incrementDateTime(input, minutes);
        Assert.assertEquals("2018 139 19:50:15", actual);
    }

    @Test
    public void incrementDateTimeTest2() {
        LocalDateTime input = LocalDateTime.parse("2017-05-05T15:20");
        int minutes = 20;
        String actual = Main.incrementDateTime(input, minutes);
        Assert.assertEquals("2017 125 15:40", actual);
    }

    @Test
    public void incrementDateTimeTest3() {
        LocalDateTime input = LocalDateTime.parse("2017-01-01T01:01:01");
        int minutes = 5_000_000;
        String actual = Main.incrementDateTime(input, minutes);
        Assert.assertEquals("2026 186 06:21:01", actual);
    }
}
