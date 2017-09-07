package lcpr.sc;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.Assert.*;

public class PClassFilterPrototypeTest {

    private static final String[][] DATA_SET = new String[][]{
            {"<root/>", // input XML
                    "<root></root>",}, // expected XML

            {"<root><p class=\"sect\">Section 1</p></root>", // input XML
                    "<root><p class=sect>Section 1</p></root>",}, // expected XML

            {"<root><p class=\"section\">Section 1</p></root>", // input XML
                    "<root><sip>Section 1</sip></root>",}, // expected XML

            {"<root><p class=\"section\">(b)</p></root>", // input XML
                    "<root><smp>(b)</smp></root>",}, // expected XML

            {"<root><p class=\"statutesubsection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutesection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statuteldpcsubsection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"clause\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"subsection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"subparagraph\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"paragraph\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutearticle\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statuteclause\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statuteparagraph\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutesubparagraph\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"sectionheading\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutesectionheading\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutesubsection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statuteclause\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"statutesubclause\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

            {"<root><p class=\"tablesubsection\">TO SMP</p></root>", // input XML
                    "<root><smp>TO SMP</smp></root>",}, // expected XML

    };

    private static PClassFilterPrototype filter;

    @BeforeClass
    public static void init() {
        filter = new PClassFilterPrototype();
    }

    @Test
    public void testDataSets() throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new PClassFilterPrototype());
        ByteArrayOutputStream baos;
        PrintStream ps;
        PrintStream old;
        old = System.out;
        for (String[] pair : DATA_SET) {
            String input = pair[0];
            String expected = pair[1];
            baos = new ByteArrayOutputStream();
            ps = new PrintStream(baos);
            System.setOut(ps);
            p.parse(new InputSource(new StringReader(input)));
            System.out.flush();
            System.setOut(old);
            String actual = baos.toString();
            Assert.assertEquals(expected, actual);
        }
    }
}
