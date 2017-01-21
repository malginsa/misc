package patterns.ducks;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        MallardDuck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        ModelDuck model = new ModelDuck();
        model.display();
        model.performQuack();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
