package xml.jaxb.params;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class Converter
{
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "root")
    static class ParamsHolder
    {
        @XmlElement(name = "param", required = true)
        private List<Parameter> params;

        public List<Parameter> getParams()
        {
            return params;
        }

        public void setParams(List<Parameter> params)
        {
            this.params = params;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    static class Parameter
    {
        @XmlElement(name = "name", required = true)
        private String name;

        @XmlElement(name = "value")
        private String value;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "ParamHolder{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws JAXBException
    {

        ParamsHolder rootElement = (ParamsHolder) JAXBContext.newInstance(ParamsHolder.class)
                .createUnmarshaller()
                .unmarshal(new StringReader(
                        "<root>\n" +
                                "\t<param> <name>sste</name> <value>(a)</value> </param>\n" +
                                "\t<param> <name>scp3</name> </param>\n" +
                                "\t<param> <name>class.number</name> <value>25</value> </param>\n" +
                        "</root>"));

        HashMap<String, String> map = new HashMap<>();
        for (Parameter param : rootElement.getParams())
        {
            map.put(param.getName(), param.getValue());
        }

        for (String key : map.keySet())
        {
            System.out.println(key + " " + map.get(key));
        }
    }
}
