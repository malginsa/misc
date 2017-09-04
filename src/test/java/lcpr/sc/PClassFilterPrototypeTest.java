package lcpr.sc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class PClassFilterPrototypeTest {

    private static final String[][] DATA_SET = new String[][]{
            {"<root/>", // input XML
                    "<root></root>",}, // expected XML

            {"<root><p class=\"sect\">Section 1</p></root>", // input XML
                    "<root><p class=\"sect\">Section 1</p></root>",}, // expected XML

            {"<root><p class=\"section\">Section 1</p></root>", // input XML
                    "<root><sip>Section 1</sip></root>",}, // expected XML

            {"<root><p class=\"section\">(b)</p></root>", // input XML
                    "<root><sip>(b)</sip></root>",}, // expected XML

    };

    private static PClassFilterPrototype filter;

    @BeforeClass
    public static void init() {
        filter = new PClassFilterPrototype();
    }

    @Test
    public void test1() throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new PClassFilterPrototype());
// TODO
        p.parse(new InputSource(new StringReader(DATA_SET[1][0])));
    }

}