package patterns.ducks;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" .. can quack");
    }
}
