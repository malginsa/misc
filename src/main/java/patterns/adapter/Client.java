package patterns.adapter;

public class Client {
    public static void main(String[] args) {

        Duck alaDuck = new TurkeyAdapter(new WildTurkey());
        alaDuck.quack();
        alaDuck.fly();
    }
}
