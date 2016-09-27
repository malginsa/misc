package patterns.decorator;

public class Orc implements Hostile {

    @Override
    public void attack() {
        System.out.println("Wheee!");
    }

    @Override
    public int getAttackPower() {
        return 5;
    }

    @Override
    public void fleeBattle() {
        System.out.println("Smart orc are going to fight to the end...");;
    }
}
