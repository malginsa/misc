package xml.jaxb.parser;

import xml.jaxb.entity.RootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class PurgeConfReader {

    public static void main(String[] args) throws JAXBException {

        File file = new File("src/main/resources/purge.cfg.xml");
        RootElement rootElement = (RootElement) JAXBContext.newInstance(RootElement.class)
                .createUnmarshaller()
                .unmarshal(file);
        System.out.println(rootElement);

    }
}
