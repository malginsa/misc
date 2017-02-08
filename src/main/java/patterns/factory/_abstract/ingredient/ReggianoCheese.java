package patterns.factory._abstract.ingredient;

import patterns.factory._abstract.ingredient.Cheese;

public class ReggianoCheese implements Cheese {
    @Override
    public void descr() {
        System.out.println("  with reggiano cheese");
    }
}
