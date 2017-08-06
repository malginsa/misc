package challenge;

import org.junit.Assert;
import org.junit.Test;

import static challenge.RockCollection.*;

public class RockCollectionTest {

    /**
     * Dennis's version
     */
    @Test
    public void gemstones1Test() {
        Assert.assertEquals( "test 1 failed", 2,
                gemstones1(new String[] {"abcdde", "baccd", "eeabg"}));
        Assert.assertEquals( "test 2 failed", 1,
                gemstones1(new String[] {"a", "abb", "abcccc"}));
    }

    /**
     * Streamed version
     */
    @Test
    public void gemstones2Test() {
        Assert.assertEquals( "test 1 failed", 2,
                gemstones2(new String[] {"abcdde", "baccd", "eeabg"}));
        Assert.assertEquals( "test 2 failed", 1,
                gemstones2(new String[] {"a", "abb", "abcccc"}));
    }
}
