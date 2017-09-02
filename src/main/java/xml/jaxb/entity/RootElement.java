package xml.jaxb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"description", "elements"})
public class RootElement {

    private String description;
    private List<Element> elements;

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Element> getElements() {
        return elements;
    }

    @XmlElement(name = "element")
    @XmlElementWrapper
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String res = "RootElement{" +
                "description='" + description + '\'' +
                ", elements={";
        if (null != elements) {
            res += elements.toString();
        }
        res += "}}";
        return res;
    }
}
