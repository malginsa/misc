package patterns.factory.v3;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return ThinCrustDough;
    }

    @Override
    public Sauce createSauce() {
        return MarinaraSauce;
    }

    @Override
    public Cheese createCheese() {
        return ReggianoCheese;
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
        return new veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
