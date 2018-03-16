/*
 * Copyright 2018: Thomson Reuters Global Resources. All Rights Reserved.
 *
 * Proprietary and Confidential information of TRGR.
 * Disclosure, Use or Reproduction without the written authorization of TRGR is prohibited.
 */
package lcpr.sc;

import com.thomsonreuters.codes.lcpr.filterchain.LcprFilterBase;
import com.thomsonreuters.codes.lcpr.sc.xampex.ScTag;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Source Conversion: TODO
 *
 * @author <a href="mailto:sergei.malgin@thomsonreuters.com">Sergei Malgin</a> UC231018.
 */

public class ElementBuilder extends LcprFilterBase
{
    private static final Logger LOG = Logger.getLogger(ElementBuilder.class);
    private static final AttributesImpl ATTRIBUTES_EMPTY = new AttributesImpl();

    private boolean inElement;
    private StringBuilder builder;
    private int nestingLevel;
    private ElementContext elementContext;
    private List<ElementObserver> observers;

    /**
     * New element creation
     *
     * @param tag   tag name
     * @param value value of element
     * @throws SAXException
     */
    private void createElement(String tag, String value) throws SAXException
    {
        if (tag == null)
        {
            throw new IllegalArgumentException("tag of element can't be null");
        }
        if (value == null)
        {
            throw new IllegalArgumentException("value of element can't be null");
        }
        String loweredCaseTag = tag.toLowerCase();
        super.startElement("", loweredCaseTag, loweredCaseTag, ATTRIBUTES_EMPTY);
        super.characters(value);
        super.endElement("", loweredCaseTag, loweredCaseTag);
    }

    private void createElement(ScTag tag, String value) throws SAXException
    {
        String name = tag.getTag();
        this.createElement(name, value);
    }

    private void notifyObservers()
    {
        for (ElementObserver observer : observers)
        {
            observer.handleEvent(elementContext);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException
    {
        if (StringUtils.equalsIgnoreCase(qName, ScTag.XAMPEX.getTag()))
        {
            super.startElement(uri, localName, qName, attributes);
            nestingLevel++;
            return;
        }
        if (nestingLevel == 1)
        {
            builder = new StringBuilder();
            nestingLevel++;
            return;
        }
        if (nestingLevel == 2)
        {
            builder.append("<").append(qName).append(">");
            return;
        }
        throw new SAXException("start tag " + qName +
                " is encountered unexpectedly on nesting level" + nestingLevel);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {
        if (StringUtils.equalsIgnoreCase(qName, ScTag.XAMPEX.getTag()))
        {
            super.endElement(uri, localName, qName);
            safeDecreasingOfNestingLevel();
            return;
        }
        if (nestingLevel == 1)
        {
            String text = builder.toString();
            elementContext.putNextElement(qName, text);
            builder = null;
            safeDecreasingOfNestingLevel();
            notifyObservers();
            if (!elementContext.isCurrentElementToDelete())
            {
                createElement(qName, text);
//                super.characters(new char[]{'\n'}, 0 ,1);
            }
            return;
        }
        if (nestingLevel == 2)
        {
            builder.append("</").append(qName).append(">");
            safeDecreasingOfNestingLevel();
            return;
        }
        throw new SAXException("end tag " + qName +
                " is encountered unexpectedly on nesting level" + nestingLevel);
    }

    public void characters(char ch[], int start, int length)
            throws SAXException
    {
        if (nestingLevel == 2)
        {
            builder.append(ch, start, length);
            return;
        }
        if (nestingLevel == 1)
        {
            super.characters(ch, start, length);
            return;
        }
    }

}
