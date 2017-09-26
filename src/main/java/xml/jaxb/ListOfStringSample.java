package xml.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListOfStringSample {

    @XmlRootElement(name = "list")
    static class ListHolder {

        private List<String> list;

        public List<String> getList() {
            return list;
        }

        @XmlElement(name = "item")
        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public static void main(String[] args) throws JAXBException {

        File file = new File("src/main/resources/listOfStringSample.xml");
        ListHolder listHolder = (ListHolder) JAXBContext.newInstance(ListHolder.class)
                .createUnmarshaller()
                .unmarshal(file);
        List<String> list = listHolder.getList();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
