package patterns.strategy.vehicle;

public class ManualTransmission implements MovingBehavior {

    @Override
    public void start() {
        System.out.println(" .. turn 1st speed, press accelerator");
    }

    @Override
    public void stop() {
        System.out.println(" .. turn neutral speed, press brackets");
    }
}
