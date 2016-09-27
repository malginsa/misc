package patterns.composite;

public class File extends Component {

    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return super.getName() + ' ' + size;
    }

}
