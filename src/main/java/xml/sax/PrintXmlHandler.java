package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PrintXmlHandler extends DefaultHandler{

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new PrintXmlHandler());
        p.parse("src\\main\\resources\\longtitle_sample1.xml");
    }

    @Override
    public void startDocument() {
        System.out.println("[starting...]");
    }

    @Override
    public void endDocument() {
        System.out.println("\n[... ended]");
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes) {
        System.out.print("<" + qName + ">");
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        System.out.print("</" + qName + ">");
    }

    @Override
    public void characters (char ch[], int start, int length) {
        for (int i = start; i < (start + length); i++) {
            System.out.print(ch[i]);
        }
    }

}
