package patterns.strategy.vehicle;

public class Tesla extends Vehicle {

    public Tesla() {
        setMovingBehavior(new AutoTransmission());
    }

    @Override
    void display() {
        System.out.println("Tesla");
    }
}
