package xml.jdom;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class XmlReader {

    public static void main(String[] args) throws JDOMException, IOException {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("src\\main\\resources\\jdom_sample.xml");

        Document document = builder.build(xmlFile);
        Element root = document.getRootElement();

        root.getChildren().forEach(staff -> {
            Element node = (Element) staff;
            node.getChildren().forEach(field -> {
                Element el = (Element) field;
                System.out.println(el.getName() + ": " + el.getValue());
            });
        });
    }
}
