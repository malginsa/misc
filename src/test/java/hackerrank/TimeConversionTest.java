package hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class TimeConversionTest {

    @Test
    public void timeConversion() {
        TimeConversion timeConversion = new TimeConversion();
        Assert.assertEquals("7:17:7", timeConversion.from12to24("7:17:7AM"));
        Assert.assertEquals("19:17:", timeConversion.from12to24("7:17PM"));
        Assert.assertEquals("12:00:00", timeConversion.from12to24("12:00:00PM"));
        Assert.assertEquals("00:00:00", timeConversion.from12to24("12:00:00AM"));
        Assert.assertEquals("23:11:11", timeConversion.from12to24("11:11:11PM"));
        Assert.assertEquals("11:11:11", timeConversion.from12to24("11:11:11AM"));
    }
}