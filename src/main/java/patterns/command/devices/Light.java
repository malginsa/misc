package patterns.command.devices;

public class Light {

    final String description;

    public Light(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println("light in " + description + " is on");
    }

    public void off() {
        System.out.println("light in " + description + "is off");
    }
}
