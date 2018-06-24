package xml.jaxb.purge.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = {"tag", "attributes"})
public class Element {

    private String tag;
    private List<Attribute> attributes;

    public String getTag() {
        return tag;
    }

    @XmlElement
    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    @XmlElement(name = "attribute")
    @XmlElementWrapper
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String res = "Element{" +
                "tag='" + tag + '\'' +
                ", attributes={";
        if (null != attributes) {
            res += attributes.toString();
        }
        res += "}}";
        return res;
    }
}
