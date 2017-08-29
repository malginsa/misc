package lcpr.sc;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class TableXmlReader {

    private static final String FILE_NAME = "src/main/resources/SLPCT2HX.xml";
    private static final String[][] convertionTable = new String[256][2];

    private static class ReaderBySax extends DefaultHandler{

        private boolean inTo = false;
        private boolean inFrom = false;
        private String accumulator = null;
        private int index = -1;

        @Override
        public void startElement (String uri, String localName, String qName, Attributes attributes) {
            if ("from".equalsIgnoreCase(qName)) {
                inFrom = true;
            }
            if ("to".equalsIgnoreCase(qName)) {
                inTo = true;
            }
        }

        @Override
        public void endElement (String uri, String localName, String qName) {
            if ("from".equalsIgnoreCase(qName)) {
                inFrom = false;
            }
            if ("to".equalsIgnoreCase(qName)) {
                if (null != accumulator) {
                    convertionTable[index][1] = accumulator;
                    accumulator = null;
                }
                inTo = false;
            }
        }

        @Override
        public void characters (char ch[], int start, int length) {
            if (!inFrom && !inTo) {
                return;
            }
            String value = new String(ch, start, length);
            if (value.contains("\"") || value.contains("\'") || (null != accumulator)) {
                if (null == accumulator) {
                    accumulator = value;
                } else {
                    accumulator += value;
                }
                return;
            }
            if (inFrom) {
                index++;
                convertionTable[index][0] = value;
                convertionTable[index][1] = "";
                return;
            }
            if (inTo) {
                convertionTable[index][1] = value;
                return;
            }
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
}
