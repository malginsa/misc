package lcpr.sc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrintHandler extends DefaultHandler{

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        System.out.print("<" + qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getQName(i);
            String value = attributes.getValue(name);
            System.out.print(" " + name + "=" + value);
        }

        System.out.print(">");
    }

    @Override
    public void endElement (String uri, String localName, String qName)
            throws SAXException {
        System.out.print("</" + qName + ">");
    }

    @Override
    public void characters (char ch[], int start, int length)
            throws SAXException {
        for (int i = start; i < (start + length); i++) {
            System.out.print(ch[i]);
        }
    }
}
