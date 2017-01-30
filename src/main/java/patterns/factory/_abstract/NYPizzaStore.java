package patterns.factory._abstract;

public class NYPizzaStore extends PizzaStore {

    @Override
    Pizza createPizza(String item) {

        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (item.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (item.equals("veggie")) {
            // ...
        } else if (item.equals("clam")) {
            // ...
        } else if (item.equals("pepperoni")) {
            // ...
        }
        return pizza;
    }
}
