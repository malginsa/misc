package xml.jaxb.parser;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface MyParser {
    Object getObject(File file, Class c) throws JAXBException;
    void saveObject(File file, Object o) throws JAXBException;
}
