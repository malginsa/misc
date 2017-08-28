package lcpr.sc;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadTableFromXml {

    public static void main(String[] args) {
        readByJdom();
    }

    public static void readByJdom() {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("src/main/resources/SLPCT2HX.xml");

        Document document = null;
        try {
            document = builder.build(xmlFile);
        } catch (JDOMException | IOException e) {
//            LOG.ERR
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List rules = root.getChildren("rule");
        System.out.println(rules.size());
    }
}
