package lcpr.sc;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import xml.sax.MyHandler;

import java.io.IOException;
import java.util.regex.Pattern;

public class Parser {

    public static void main(String[] args) throws SAXException, IOException {
        Pattern pattern;
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new LongTitleFilter());
        p.parse("src\\main\\resources\\longtitle_sample1.xml");
    }
}
