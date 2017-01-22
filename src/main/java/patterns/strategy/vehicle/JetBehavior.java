package patterns.strategy.vehicle;

public class JetBehavior implements MovingBehavior {

    @Override
    public void start() {
        System.out.println(" .. press fuel button");
    }

    @Override
    public void stop() {
        System.out.println(" .. stop the flow of fuel");
    }
}
