package patterns.factory._abstract.ingredient;

import patterns.factory._abstract.ingredient.Dough;

public class ThinCrustDough implements Dough {

    @Override
    public void discription() {
        System.out.println("  thin crust dough");
    }
}
