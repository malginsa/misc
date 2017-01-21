package patterns.ducks;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" .. can fly with wings");
    }
}
