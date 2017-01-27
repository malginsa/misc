package patterns.factory;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "cheese" :
                pizza = new ChicagoStyleCheesePizza();
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
