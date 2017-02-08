package patterns.factory._abstract;

import patterns.factory._abstract.ingredient.Cheese;
import patterns.factory._abstract.ingredient.Dough;
import patterns.factory._abstract.ingredient.Sauce;

public abstract class Pizza {

    String name;
    Dough dough;
    Sauce sauce;
    Cheese cheese;
//    Veggies veggies[];
//    Pepperoni pepperoni;
//    Clams clam;

    String description = "Abstract pizza";

    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
