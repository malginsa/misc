package xml.jaxb.params;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Converter
{

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "params")
    static class ParamsHolder
    {
        @XmlElement(name = "name", required = true)
        List<String> names = new ArrayList<String>();

        @XmlElement(name = "value", required = false)
        List<String> values = new ArrayList<String>();

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }

        @Override
        public String toString()
        {
            return "ParamsHolder{" +
                    "names=" + names +
                    ", values=" + values +
                    '}';
        }
    }

    public static void main(String[] args) throws JAXBException
    {

        ParamsHolder paramsHolder = (ParamsHolder) JAXBContext.newInstance(ParamsHolder.class)
                .createUnmarshaller()
                .unmarshal(new StringReader("<params>\n" +
                        "\t<name>sste</name><value>(a)</value>\n" +
                        "\t<name>scp3</name><value>2(ip)</value>\n" +
                        "\t<name>class.number</name><value>25</value>\n" +
                        "</params>\n"));
        System.out.println(paramsHolder);
    }
}
