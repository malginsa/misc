// TODO

package lcpr.sc;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * TODO
 */
public class PClassFilterPrototype extends PrintHandler {
    //extends LcprFilterBase {

    AttributesImpl EMPTY_ATTRIBUTES = new AttributesImpl();

    private boolean inClassSection = false;
    private boolean conversionToSmp = false;
    private boolean conversionToSip = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (!StringUtils.equalsIgnoreCase(qName, "p")) {
            super.startElement(uri, localName, qName, attributes);
            return;
        }
        String value = attributes.getValue("class");
        value = value.toLowerCase();
        if (null == value) {
            super.startElement(uri, localName, qName, attributes);
            return;
        }
        if (StringUtils.equalsIgnoreCase(qName, "section")) {
            inClassSection = true;
            return;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO to deal with enclosed elements
        if (!inClassSection) {
            super.endElement(uri, localName, qName);
            return;
        }
        if (conversionToSip || conversionToSmp) {
            super.endElement(uri, localName, qName);
            return;
        }

    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        String text = new String(ch, start, length);
        String substring_0_7 = text.substring(0, 7);
        if (inClassSection && "Section".equals(substring_0_7)) {
            conversionToSip = true;
            super.startElement("", "", "sip", EMPTY_ATTRIBUTES);
            super.characters(ch, start, length);
            return;
        }
        if (inClassSection) {
            conversionToSmp = true;
            super.startElement("", "", "smp", EMPTY_ATTRIBUTES);
            super.characters(ch, start, length);
            return;
        }
        super.characters(ch, start, length);
    }
}
