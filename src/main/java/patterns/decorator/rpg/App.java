package patterns.decorator.rpg;

public class App {
    public static void main(String[] args) {

        // simple troll
        System.out.println("A simple looking troll approaches.");
        Hostile troll = new Troll();
        troll.attack();
        troll.fleeBattle();
        System.out.printf("Simple troll power %d.\n", troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        System.out.println("\nA smart looking troll surprises you.");
        Hostile smart = new Smart(troll);
        smart.attack();
        smart.fleeBattle();
        System.out.printf("Smart troll power %d.\n", smart.getAttackPower());

        System.out.println("\nsuddenly, smart orc appeares behind you!");
        final Smart smartOrc = new Smart(new Orc());
        smartOrc.attack();
        smartOrc.fleeBattle();
        System.out.printf("Smart orc power %d.\n", smartOrc.getAttackPower());

    }
}
