package patterns.strategy.vehicle;

public abstract class Vehicle {

    private MovingBehavior movingBehavior;

    abstract void display();

    void recharge() {
        System.out.println(" .. recharge me");
    }

    void performStart() {
        movingBehavior.start();
    }

    void performStop() {
        movingBehavior.stop();
    }

    public void setMovingBehavior(MovingBehavior movingBehavior) {
        this.movingBehavior = movingBehavior;
    }
}
