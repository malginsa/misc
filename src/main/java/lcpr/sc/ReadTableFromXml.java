package lcpr.sc;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadTableFromXml {

    private static final String FILE_NAME = "src\\main\\resources\\SLPCT2HX.xml";


    private static class ReaderBySax extends DefaultHandler{

        @Override
        public void startElement (String uri, String localName, String qName, Attributes attributes) {
            System.out.print("<" + qName + ">");
        }

        @Override
        public void endElement (String uri, String localName, String qName) {
            System.out.print("</" + qName + ">\n");
        }

        @Override
        public void characters (char ch[], int start, int length) {
//            for (int i = start; i < (start + length); i++) {
//                System.out.print(ch[i]);
//            }
        }

    }


    public static void main(String[] args) throws Exception {
        readBySax();
    }

    public static void readBySax() throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(new ReaderBySax());
        xmlReader.parse(FILE_NAME);
    }

    public static void readByJdom() throws Exception {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(FILE_NAME);

        Document document = null;
        try {
            document = builder.build(xmlFile);
        } catch (JDOMException | IOException e) {
//            LOG.ERR
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        if (null == root) {
            throw new Exception("Error root element");
        }
        List rules = root.getChildren("rule");
        System.out.println(rules.size());
    }

}
