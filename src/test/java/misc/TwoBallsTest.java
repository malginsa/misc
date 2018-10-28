package misc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoBallsTest {

    private TwoBalls twoBalls;

    @Before
    public void init() {
        twoBalls = new TwoBallsOptimized();
    }

    @Test
    public void numberOfFloors11() {
        int actual = twoBalls.numberOfFloors(1, 1);
        Assert.assertEquals(1, actual);
    }

    @Test
    public void numberOfFloors12() {
        int actual = twoBalls.numberOfFloors(1, 4);
        Assert.assertEquals(3, actual);
    }

    @Test
    public void numberOfFloors21() {
        int actual = twoBalls.numberOfFloors(2, 10);
        Assert.assertEquals(4, actual);
    }

    @Test
    public void numberOfFloors22() {
        int actual = twoBalls.numberOfFloors(2, 21);
        Assert.assertEquals(6, actual);
    }

    @Test
    public void numberOfFloors31() {
        int actual = twoBalls.numberOfFloors(3, 10);
        Assert.assertEquals(3, actual);
    }

    @Test
    public void numberOfFloors32() {
        int actual = twoBalls.numberOfFloors(3, 12);
        Assert.assertEquals(4, actual);
    }
}