package patterns.factory._abstract;

import patterns.factory._abstract.ingredient.Cheese;
import patterns.factory._abstract.ingredient.Dough;
import patterns.factory._abstract.ingredient.Sauce;

public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
//    public Veggies[] createVeggies();
//    public Pepperoni createPepperoni();
//    public Clams createClam();
}
