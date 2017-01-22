package patterns.strategy.ducks;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" .. can't make a sound");
    }
}
