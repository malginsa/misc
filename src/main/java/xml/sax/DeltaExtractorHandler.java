package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * Extracts delta element from xml document
 *
 * @author <a href="mailto:sergei.malgin@thomsonreuters.com">Sergei Malgin</a> UC231018.
 */
public class DeltaExtractorHandler extends DefaultHandler
{
    public static final String ID_ATTRIBUTE_NAME = "ID";
    public static final String DELTA_TAG_NAME = "delta";

    private String deltaId;
    private StringBuilder stringBuilder;
    private String deltaElement;

    public DeltaExtractorHandler(String deltaId)
    {
        this.deltaId = (deltaId != null) ? deltaId : "";
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes)
    {
        String id = attributes.getValue(ID_ATTRIBUTE_NAME);
        if (DELTA_TAG_NAME.equalsIgnoreCase(qName) &&
                deltaId.equalsIgnoreCase(id))
        {
            stringBuilder = new StringBuilder();
        }
        if (stringBuilder != null)
        {
            addStartTagToStringBuilder(qName, attributes);
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName)
    {
        if (stringBuilder != null)
        {
            stringBuilder.append("</").append(qName).append(">");
            if (DELTA_TAG_NAME.equalsIgnoreCase(qName))
            {
                deltaElement = stringBuilder.toString();
                stringBuilder = null;
            }
        }
    }

    @Override
    public void characters (char ch[], int start, int length)
    {
        if(stringBuilder != null)
        {
            stringBuilder.append(ch, start, length);
        }
    }

    private void addStartTagToStringBuilder(String qName, Attributes attributes)
    {
        stringBuilder.append("<").append(qName);
        for (int i = 0; i < attributes.getLength(); i++)
        {
            stringBuilder.append(" "+ attributes.getQName(i)+"=\""+ attributes.getValue(i)+"\" ");
        }
        stringBuilder.append(">");
    }

    public String getDeltaElement()
    {
        return deltaElement;
    }
}
