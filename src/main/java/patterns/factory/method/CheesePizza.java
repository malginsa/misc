package patterns.factory.method;

class CheesePizza extends Pizza {

    CheesePizza() {
        description = "Cheese pizza";
    }

    @Override
    public void prepare() {
        description += "  rice added";
    }

    @Override
    public void bake() {
        description += "  no needs to bake";
    }

    @Override
    public void cut() {
        description += "  cut";
    }

    @Override
    public void box() {
        description += "  boxed in cheap cardboard box";
    }
}
