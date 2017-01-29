package patterns.factory.v2;

public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "cheese" :
                pizza = new NYStyleCheesePizza();
                break;
            case "pepperoni":
//                pizza = new PepperoniPizza();
                break;
            case "clam":
//                pizza = new ClamPizza();
                break;
            case "veggie":
//                pizza = new VeggiePizza();
                break;
        }
        return pizza;
    }
}
