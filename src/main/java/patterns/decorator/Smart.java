package patterns.decorator;

public class Smart implements Hostile {

    private final Hostile hostile;

    public Smart(Hostile hostile) {
        this.hostile = hostile;
    }

    @Override
    public void attack() {
        System.out.println("It throws a rock at you!");
        hostile.attack();
    }

    @Override
    public int getAttackPower() {
        return hostile.getAttackPower() + 30;
    }

    @Override
    public void fleeBattle() {
        System.out.println("It calls for help!");
        hostile.fleeBattle();
    }
}
