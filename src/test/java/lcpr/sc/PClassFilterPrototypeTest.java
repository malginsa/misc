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

            {"<root><p class=\"chapter\">CHAPTER 1</p></root>", // input XML
                    "<root><hg9>CHAPTER 1</hg9></root>",}, // expected XML

            {"<root><p class=\"chapter\">[CHAPTER</p></root>", // input XML
                    "<root><hg9>[CHAPTER</hg9></root>",}, // expected XML

            {"<root><p class=\"chapter\">¢aSUBCHAPTER _</p></root>", // input XML
                    "<root><hg9>¢aSUBCHAPTER _</hg9></root>",}, // expected XML

            {"<root><p class=\"chapter\">_SUBCHAPTER</p></root>", // input XML
                    "<root><hg9c>_SUBCHAPTER</hg9c></root>",}, // expected XML

            {"<root><p class=\"article\">TO CLPP</p></root>", // input XML
                    "<root><clpp>TO CLPP</clpp></root>",}, // expected XML

            {"<root><p class=\"part\">TO CLPP</p></root>", // input XML
                    "<root><clpp>TO CLPP</clpp></root>",}, // expected XML

            {"<root><p class=\"subpart\">TO CLPP</p></root>", // input XML
                    "<root><clpp>TO CLPP</clpp></root>",}, // expected XML

            {"<root><p class=\"section\">Section<p></p></p></root>", // input XML
                    "<root><sip>Section<p></p></sip></root>",}, // expected XML
    };

    private static HtmlTagFilter filter;

    @BeforeClass
    public static void init() {
        filter = new HtmlTagFilter();
    }

    @Test
    public void testDataSets() throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new HtmlTagFilter());
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
