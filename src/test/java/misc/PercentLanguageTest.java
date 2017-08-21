package misc;

import org.junit.Assert;
import org.junit.Test;

public class PercentLanguageTest {

    @Test
    public void test1() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform(""), "" );
    }

    @Test
    public void test2() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("123"), "123" );
    }

    @Test
    public void test3() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("%0A"), "10" );
    }

    @Test
    public void test4() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("%11"), "17" );
    }

    @Test
    public void test5() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("%111"), "171" );
    }

    @Test
    public void test6() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("8%11a"), "817a" );
    }

    @Test
    public void test7() {
        PercentLanguage psm = new PercentLanguage();
        Assert.assertEquals( psm.transform("1%11%111%1111"), "1171711711" );
    }

}