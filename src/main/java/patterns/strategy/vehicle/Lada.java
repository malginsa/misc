package patterns.strategy.vehicle;

public class Lada extends Vehicle {

    public Lada() {
        setMovingBehavior(new ManualTransmission());
    }

    @Override
    void display() {
        System.out.println("Lada");
    }
}
