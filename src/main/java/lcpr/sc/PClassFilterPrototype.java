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

        // ?
        if (StringUtils.equalsIgnoreCase(qName, "u")) {
            super.startElement("", "", "centa", EMPTY_ATTRIBUTES);
            return;
        }

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
        // <p class=

        if (StringUtils.equalsIgnoreCase(value, "statutesubsection")
                || StringUtils.equalsIgnoreCase(value, "statutesection")
                || StringUtils.equalsIgnoreCase(value, "statuteldpcsubsection")
                || StringUtils.equalsIgnoreCase(value, "clause")
                || StringUtils.equalsIgnoreCase(value, "subsection")
                || StringUtils.equalsIgnoreCase(value, "subparagraph")
                || StringUtils.equalsIgnoreCase(value, "paragraph")
                || StringUtils.equalsIgnoreCase(value, "statutearticle")
                || StringUtils.equalsIgnoreCase(value, "statuteclause")
                || StringUtils.equalsIgnoreCase(value, "statuteparagraph")
                || StringUtils.equalsIgnoreCase(value, "statutesubparagraph")
                || StringUtils.equalsIgnoreCase(value, "sectionheading")
                || StringUtils.equalsIgnoreCase(value, "statutesectionheading")
                || StringUtils.equalsIgnoreCase(value, "statutesubsection")
                || StringUtils.equalsIgnoreCase(value, "statuteclause")
                || StringUtils.equalsIgnoreCase(value, "statutesubclause")
                || StringUtils.equalsIgnoreCase(value, "tablesubsection")
                ) {
            inClassSection = true;
            conversionToSmp = true;
            super.startElement("", "", "smp", EMPTY_ATTRIBUTES);
            return;
        }

        if (StringUtils.equalsIgnoreCase(value, "section")) {
            inClassSection = true;
            return;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        // ?
        if (StringUtils.equalsIgnoreCase(qName, "u")) {
            super.endElement("", "", "centa");
            return;
        }

        // TODO to deal with enclosed elements
        if (!inClassSection) {
            super.endElement(uri, localName, qName);
            return;
        }

        if (inClassSection && "p".equalsIgnoreCase(qName)) {
            inClassSection = false;
            if (conversionToSip) {
                conversionToSip = false;
                super.endElement("", "", "sip");
                return;
            }
            if (conversionToSmp) {
                conversionToSmp = false;
                super.endElement("", "", "smp");
                return;
            }
        }

        super.endElement(uri, localName, qName);
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {

        if (!inClassSection) {
            super.characters(ch, start, length);
            return;
        }
        String text = new String(ch, start, length);
        text = text.trim();
        if (StringUtils.isEmpty(text)) {
            return;
        }

        if (conversionToSip || conversionToSmp) {
            super.characters(ch, start, length);
            return;
        }

        String substring_0_7 = null;
        if (text.length() > 6) {
            substring_0_7 = text.substring(0, 7);
        }
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
