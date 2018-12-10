package xml.xslt;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

public class XPathSample {

    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("src/main/resources/xpath-sample.xml");
        InputSource is = new InputSource(inputStream);

        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        Document document = df.newDocumentBuilder().parse(is);

        NodeIterator nodeIterator = XPathAPI.selectNodeIterator(document, "/root/books");
        Node node;
        while ((node = nodeIterator.nextNode()) != null) {
            if (node.getNodeType() == Node.TEXT_NODE) {
                System.out.println(node.getNodeValue());
            } else {
                System.out.println(node.getTextContent());
            }
        }
    }
}
