package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{

    @Override
    public void startDocument() {
        System.out.println("starting...");
    }

    @Override
    public void endDocument() {
        System.out.println("\n... ended");
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
