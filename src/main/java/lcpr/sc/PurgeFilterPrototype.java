// TODO

package lcpr.sc;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import xml.jaxb.entity.Attribute;
import xml.jaxb.entity.Element;
import xml.jaxb.entity.RootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * TODO
 */

public class PurgeFilterPrototype extends PrintHandler {
    //extends LcprFilterBase {

    private List<Element> blackList;
    private boolean skipping = false;
    private int enclosedTags = 0;

    @Override
    public void startDocument() throws SAXException {
        // read config file name from parameter
        String configFileName = "src/main/resources/purge.cfg.xml";
        File file = new File(configFileName);
        RootElement rootElement = null;
        try {
            rootElement = (RootElement) JAXBContext.newInstance(RootElement.class)
                    .createUnmarshaller()
                    .unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            // TODO LOG.error("configuration file " + file)
        }
        blackList = rootElement.getElements();
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (skipping) {
            enclosedTags++;
            return;
        }
        if (isElementInBlackList(qName, attributes)) {
            skipping = true;
            enclosedTags = 1;
            return;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    protected boolean isElementInBlackList(String qName, Attributes attributes) {
        for (Element element : blackList) {
            if (StringUtils.equalsIgnoreCase(qName, element.getTag()) &&
                    isSubSet(element.getAttributes(), attributes)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isSubSet(List<Attribute> blackListedAttributes, Attributes attributes) {
        int count = 0;
        for (Attribute blackListedAttribute : blackListedAttributes) {
            String name = blackListedAttribute.getName();
            String value = blackListedAttribute.getValue();
            String attributesValue = attributes.getValue(name);
            if (StringUtils.equalsIgnoreCase(value, attributesValue)) {
                count++;
            }
        }
        boolean result = (count == blackListedAttributes.size());
        return result;
    }

    @Override
    public void endElement (String uri, String localName, String qName)
            throws SAXException {
        if (skipping) {
            enclosedTags--;
            if (enclosedTags < 1) {
                skipping = false;
            }
            return;
        }
        super.endElement(uri, localName, qName);
    }

    public void characters (char ch[], int start, int length)
            throws SAXException {
        if (!skipping) {
            super.characters(ch, start, length);
        }
    }

}
