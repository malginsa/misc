package xml.sax;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

public class DeltaExtractorHandlerTest
{
    public static final String DOCUMENT_SAMPLE = "<root>" +
            "<alpha ID=\"123\">Alpha text</alpha>" +
            "<delta ID=\"123\">Content of <italic style=\"bold\">Delta</italic> element</delta>" +
            "<delta ID=\"4\">fake</delta>" +
            "</root>";
    public static final String DELTA_ELEMENT = "<delta ID=\"123\" >Content of <italic style=\"bold\" >Delta</italic> element</delta>";

    @Test
    public void testSimpleDelta() throws ParserConfigurationException,
            SAXException, IOException
    {
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        DeltaExtractorHandler handler = new DeltaExtractorHandler("123");
        saxParser.parse(new InputSource(new StringReader(DOCUMENT_SAMPLE)), handler);
        String expected = handler.getDeltaElement();
        Assert.assertEquals(expected, DELTA_ELEMENT);
    }
}
