package patterns.factory_simple;

abstract class Pizza {

    String description = "Abstract pizza";

    protected abstract void prepare();

    protected abstract void bake();

    protected abstract void cut();

    protected abstract void box();

    @Override
    public String toString() {
        return description;
    }
}
