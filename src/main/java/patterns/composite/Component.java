package patterns.composite;

public abstract class Component implements Item {

    private String name;

    public Component(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
