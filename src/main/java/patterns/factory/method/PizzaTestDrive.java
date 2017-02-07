package patterns.factory.method;

public class PizzaTestDrive {
    public static void main(String[] args) {

        System.out.println("Ethan ordered a "
                + new NYPizzaStore().orderPizza("cheese").getName() + "\n");
        System.out.println("Joel ordered a "
                + new ChicagoPizzaStore().orderPizza("cheese").getName() + "\n");

    }
}
