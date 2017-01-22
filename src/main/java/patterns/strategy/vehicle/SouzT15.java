package patterns.strategy.vehicle;

public class SouzT15 extends Vehicle {

    public SouzT15() {
        setMovingBehavior(new JetBehavior());
    }

    @Override
    void display() {
        System.out.println("SouzT15");
    }
}
