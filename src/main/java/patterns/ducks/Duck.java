package patterns.ducks;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract void display();

    protected void swim() {
        System.out.print(" .. any duck can swim");
    }

    protected void performFly() {
        flyBehavior.fly();
    }

    protected void performQuack() {
        quackBehavior.quack();
    }
}
