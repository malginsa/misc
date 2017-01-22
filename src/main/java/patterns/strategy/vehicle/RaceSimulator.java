package patterns.strategy.vehicle;

public class RaceSimulator {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Lada();
        vehicles[1] = new Tesla();
        vehicles[2] = new SouzT15();
        for (Vehicle vehicle : vehicles) {
            vehicle.display();
            vehicle.performStart();
            vehicle.performStop();
            vehicle.recharge();
        }
    }
}
