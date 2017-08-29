package lcpr.sc;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.util.HashMap;
import java.util.Map;

public class TableXmlReader {

    private static final String FILE_NAME = "src/main/resources/SLPCT2HX.xml";
    private static final Map<String, String> convertionTable = new HashMap<>();

    private static class RulesHandler extends DefaultHandler{

        private boolean inTo = false;
        private boolean inFrom = false;
        private String accumulator = null;
        private String currentKey = null;

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
                    if (null != currentKey) {
                        convertionTable.put(currentKey, accumulator);
                        currentKey = null;
                    } else {
// TODO LOG.error
                    }
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
                incAccumulatorSafely(value);
                return;
            }
            if (inFrom) {
                currentKey = value;
                convertionTable.put(value, "");
                return;
            }
            if (inTo) {
                convertionTable.put(currentKey, value);
                return;
            }
        }

        private void incAccumulatorSafely(String value) {
            if (null == accumulator) {
                accumulator = value;
            } else {
                accumulator += value;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        readBySax();
        convertionTable.forEach((k, v) -> {
            System.out.println("|"+k+"|\t|"+v+"|");
        });
    }

    public static void readBySax() throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(new RulesHandler());
        xmlReader.parse(FILE_NAME);
    }
}
