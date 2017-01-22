package patterns.strategy.vehicle;

public class AutoTransmission implements MovingBehavior {

    @Override
    public void start() {
        System.out.println(" .. press accelerator");
    }

    @Override
    public void stop() {
        System.out.println(" .. press brackets");
    }
}
