package lcpr.sc;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TableXmlReader {

    private static final String FILE_NAME = "src/main/resources/SLPCT2HX.xml";
    private static final String[][] convertionTable = new String[256][2];

    private static class ReaderBySax extends DefaultHandler{

        private boolean inRule = false;
        private boolean inFrom = false;
        private boolean inTo = false;
        private int index = 0;

        @Override
        public void startElement (String uri, String localName, String qName, Attributes attributes) {
            switch (qName.toLowerCase()) {
                case "rule": inRule = true;
                    break;
                case "from": inFrom = true;
                    break;
                case "to": inTo = true;
                    break;
            }
        }

        @Override
        public void endElement (String uri, String localName, String qName) {
            switch (qName.toLowerCase()) {
                case "rule": inRule = false;
                    break;
                case "from": inFrom = false;
                    break;
                case "to": inTo = false;
                    break;
            }
        }

        @Override
        public void characters (char ch[], int start, int length) {
            String value = new String(ch, start, length);
            if (inRule && inFrom) {
                convertionTable[index][0] = value;
                convertionTable[index][1] = "";
            }
            if (inRule && inTo) {
                convertionTable[index][1] = value;
                index++;
            }
        }

    }


    public static void main(String[] args) throws Exception {
        readBySax();
        for (int i = 0; i < convertionTable.length; i++) {
            System.out.println("|"+convertionTable[i][0] + "|\t|" + convertionTable[i][1] + "|");
        }
        System.out.println("|" + convertionTable[12][0] + "|");
        System.out.println("|" + convertionTable[12][1] + "|");
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
