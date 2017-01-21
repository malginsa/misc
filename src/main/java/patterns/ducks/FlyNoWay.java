package patterns.ducks;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" .. can't fly at all");
    }
}
