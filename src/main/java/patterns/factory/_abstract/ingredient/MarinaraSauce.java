package patterns.factory._abstract.ingredient;

import patterns.factory._abstract.ingredient.Sauce;

public class MarinaraSauce implements Sauce {
    @Override
    public void discription() {
        System.out.println("  with marinara sauce");
    }
}
