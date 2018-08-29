package xml.jaxb.purge.parser;

import xml.jaxb.purge.entity.Attribute;
import xml.jaxb.purge.entity.Element;
import xml.jaxb.purge.entity.RootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;

public class Creator {

    public static void main(String[] args) throws JAXBException {

        Attribute attribute1 = new Attribute();
        attribute1.setName("class1");
        attribute1.setValue("blank1");

        ArrayList<Attribute> attributes1 = new ArrayList<>();
        attributes1.add(attribute1);

        Element pElement1 = new Element();
        pElement1.setTag("p1");
        pElement1.setAttributes(attributes1);

        RootElement rootElement = new RootElement();
        rootElement.setDescription("PurgeFilter configuration file contains elements to be deleted");
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(pElement1);
        rootElement.setElements(elements);

        Attribute attribute2 = new Attribute();
        attribute2.setName("class2");
        attribute2.setValue("blank2");

        ArrayList<Attribute> attributes2 = new ArrayList<>();
        attributes2.add(attribute1);
        attributes2.add(attribute2);

        Element pElement2 = new Element();
        pElement2.setTag("p2");
        pElement2.setAttributes(attributes2);

        elements.add(pElement2);

        System.out.println(rootElement);

        File file = new File("src/main/resources/purge.cfg.xml");
        JAXBContext.newInstance(rootElement.getClass())
                .createMarshaller()
                .marshal(rootElement, file);

    }
}
