package xml.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new MyHandler());
        p.parse("test.xml");
    }
}
