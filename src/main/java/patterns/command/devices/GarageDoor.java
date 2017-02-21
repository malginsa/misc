package patterns.command.devices;

public class GarageDoor {

    public void up() {
        System.out.println("garage door is opened");
    }

    public void checkObstacles() {
        System.out.println("check obstacles behind the door");
    }

    public void down() {
        System.out.println("garage door is closed");
    }
}
