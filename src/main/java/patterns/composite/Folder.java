package patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Component {

    List<Component> components = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public Folder add(Component component) {
        components.add(component);
        return this;
    }

    @Override
    public int getSize() {
        int res = 0;
        for (Component component : components) {
            res += component.getSize();
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(super.getName());
        res.append(' ');
        res.append(getSize());
        res.append("(");
        for (Component component : components) {
            res.append(component.toString());
            res.append("; ");
        }
        res.append(")");
        return res.toString();
    }
}
