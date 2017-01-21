package patterns.ducks;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println(" .. flying with a rocket");
    }
}
