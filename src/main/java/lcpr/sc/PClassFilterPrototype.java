// TODO

package lcpr.sc;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 */
public class PClassFilterPrototype extends PrintHandler {
    //extends LcprFilterBase {

    private static final Pattern CHAPTER_VARIATIONS = Pattern.compile("^(¢a)?\\[?(SUB)?CHAPTER.*");
    private static final AttributesImpl EMPTY_ATTRIBUTES = new AttributesImpl();

    private boolean inClassSection = false;
    private boolean conversionToSmp = false;
    private boolean conversionToSip = false;
    private boolean inClassChapter = false;
    private boolean conversionToHg9 = false;
    private boolean conversionToHg9c = false;
    private boolean conversionToClpp = false;
    private int enclosedPCount = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (!StringUtils.equalsIgnoreCase(qName, "p")) {
            super.startElement(uri, localName, qName, attributes);
            return;
        }

        // p tag encountered
        if ("p".equalsIgnoreCase(qName)) {
            enclosedPCount++;
        }

        String value = attributes.getValue("class");
        if (null == value) {
            super.startElement(uri, localName, qName, attributes);
            return;
        }

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

        // <p class=section..
        if (StringUtils.equalsIgnoreCase(value, "section")) {
            inClassSection = true;
            return;
        }

        // <p class=chapter..
        if (StringUtils.equalsIgnoreCase(value, "chapter")) {
            inClassChapter = true;
            return;
        }

        if (StringUtils.equalsIgnoreCase(value, "article")
                || StringUtils.equalsIgnoreCase(value, "part")
                || StringUtils.equalsIgnoreCase(value, "subpart")) {
            conversionToClpp = true;
            super.startElement("", "", "clpp", EMPTY_ATTRIBUTES);
            return;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        // p tag encountered
        if ("p".equalsIgnoreCase(qName)) {
            enclosedPCount--;
        }

        if (inClassSection && (enclosedPCount == 0)) {
            inClassSection = false;
            if (conversionToSip) {
                conversionToSip = false;
                super.endElement("", "", "sip");
            }
            if (conversionToSmp) {
                conversionToSmp = false;
                super.endElement("", "", "smp");
            }
            return;
        }

        if (inClassChapter && (enclosedPCount == 0)) {
            inClassChapter = false;
            if (conversionToHg9) {
                conversionToHg9 = false;
                super.endElement("", "", "hg9");
            }
            if (conversionToHg9c) {
                conversionToHg9c = false;
                super.endElement("", "", "hg9c");
            }
            return;
        }

        if (conversionToClpp && (enclosedPCount == 0)) {
            conversionToClpp = false;
            super.endElement("", "", "clpp");
            return;
        }

        super.endElement(uri, localName, qName);
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {

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

        Matcher matcher = CHAPTER_VARIATIONS.matcher(text);
        if (inClassChapter && matcher.matches()) {
            conversionToHg9 = true;
            super.startElement("", "", "hg9", EMPTY_ATTRIBUTES);
            super.characters(ch, start, length);
            return;
        }
        if (inClassChapter) {
            conversionToHg9c = true;
            super.startElement("", "", "hg9c", EMPTY_ATTRIBUTES);
            super.characters(ch, start, length);
            return;
        }

        super.characters(ch, start, length);
    }
}
