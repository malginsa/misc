/*
 * CharsetEncodingFilter.java
 *
 * Created on: Aug 8, 2017 By: UC231018
 *
 * Copyright 2017: Thomson Reuters Global Resources. All Rights Reserved.
 *
 * Proprietary and Confidential information of TRGR.
 * Disclosure, Use or Reproduction without the written authorization of TRGR is prohibited.
 */
package lcpr.sc;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This filter captures text from <p class=longtitle> element.
 * The first part of the title goes into <toa> element, the remainder goes into <toc> element.
 * Element "p class=Framecontents" within "p class=longtitle" is used as a delimiter.
 *
 * @author <a href="mailto:sergei.malgin@thomsonreuters.com">Sergei Malgin</a> UC231018.
 */

// TODO Is the <p> case sensitive?

public class LongTitleFilter extends DefaultHandler{

    private static final Logger LOG = LoggerContext.getContext().getLogger(LongTitleFilter.class.toString());
    private static final String TAG_TOAC = "toac";
    private static final String TAG_TAC = "tac";
    private static final AttributesImpl ATTRIBUTES_EMPTY = new AttributesImpl();

    private int pTagCounter = 0; // counts enclosed elements with <p> tag within <p class=longtitle>
    private boolean inLongTitle = false; // we are parsing elements within <p class=longtitle>
    private boolean inFramecontents = false; // we are parsing elements within <p class=Framecontents>

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equals("p")) {
            if ("longtitle".equals(attributes.getValue("class"))) {
                inLongTitle = true;
            } else  if ("Framecontents".equals(attributes.getValue("class"))) {
                inFramecontents = true;
            }
            if (inLongTitle) {
                pTagCounter++;
            }
        }
// ?
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement (String uri, String localName, String qName)
            throws SAXException {
        if (inLongTitle && qName.equals("p")) {
            pTagCounter--;
            if (pTagCounter == 0) {
                inLongTitle = false;
                inFramecontents = false;
            }
        }
// ?
       super.endElement(uri, localName, qName);
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {
        String tag = null;
        if (inFramecontents) {
            tag = TAG_TOAC;
        } else if (inLongTitle) {
            tag = TAG_TAC;
        }
        if (null != tag) {
            String value = new String(ch, start, length);
            String trimmed = value.trim();
            char[] asChars = trimmed.toCharArray();
            if (!trimmed.isEmpty()) {
                LOG.debug("longtitle element has been converted to " + "<" + tag + ">" + trimmed + "</" + tag + ">\n");
                System.out.print("<" + tag + ">" + trimmed + "</" + tag + ">\n");
                super.startElement("", tag, tag, ATTRIBUTES_EMPTY);
                super.characters(asChars, 0, asChars.length);
                super.endElement("", tag, tag);
            }
        }
    }
}
