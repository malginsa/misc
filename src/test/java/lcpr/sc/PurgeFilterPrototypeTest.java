package lcpr.sc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLReaderFactory;
import xml.jaxb.entity.Attribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class PurgeFilterPrototypeTest {

    private static final String[][] DATA_SET = new String[][]{
            {"<root/>", // input XML
                    "<root></root>",}, // expected XML
            {"<root><p class=\"blank\"/></root>",
                    "<root></root>",},
            {"<root><p class=\"blank\">p_value</p>root_value</root>",
                    "<root>root_value</root>",},
            {"<root><p class=\"blank\" name2=\"value2\"/></root>",
                    "<root></root>",},
            {"<root><p name2=\"value2\"/></root>",
                    "<root><p name2=\"value2\"/></root>",},
            {"<root><a><p class=\"blank\"></p></a></root>",
                    "<root><a></a></root>",},
            {"<root><p class=\"blank\"><a/></p></root>",
                    "<root></root>",},
            {"<root><p class=\"blank\"><p class=\"blank\"/></p></root>",
                    "<root></root>",},
    };

    private static PurgeFilterPrototype filter;

    @BeforeClass
    public static void init() throws SAXException {
        filter = new PurgeFilterPrototype();
    }

    @Test
    public void isSubSet1() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n2", "", "v2");
        Assert.assertFalse(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void isSubSet2() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n1", "", "v1");
        Assert.assertTrue(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void isSubSet3() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n1", "", "v1");
        attributes.addAttribute("", "", "n2", "", "v2");
        Assert.assertTrue(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void isSubSet4() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        blackListed.add(new Attribute(){{ setName("n2"); setValue("v2");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n1", "", "v1");
        attributes.addAttribute("", "", "n2", "", "v2");
        Assert.assertTrue(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void isSubSet5() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        blackListed.add(new Attribute(){{ setName("n2"); setValue("v2");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n1", "", "v1");
        attributes.addAttribute("", "", "n2", "", "v2");
        attributes.addAttribute("", "", "n3", "", "v3");
        Assert.assertTrue(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void isSubSet6() {
        ArrayList<Attribute> blackListed = new ArrayList<>();
        blackListed.add(new Attribute(){{ setName("n1"); setValue("v1");}});
        blackListed.add(new Attribute(){{ setName("n2"); setValue("v2");}});
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("", "", "n2", "", "v2");
        attributes.addAttribute("", "", "n3", "", "v3");
        Assert.assertFalse(filter.isSubSet(blackListed, attributes));
    }

    @Test
    public void test1() throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new PurgeFilterPrototype());
// TODO
        p.parse(new InputSource(new StringReader(DATA_SET[7][0])));
    }
}
