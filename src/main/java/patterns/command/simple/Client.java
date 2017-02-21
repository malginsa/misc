package patterns.command.simple;

import patterns.command.devices.GarageDoor;
import patterns.command.commands.GarageDoorOpenCommand;
import patterns.command.devices.Light;
import patterns.command.commands.LightOnCommand;

public class Client {

    public static void main(String[] args) {

        RemoteControl remote = new RemoteControl();

        Light light = new Light("Living room");
        LightOnCommand lightOn = new LightOnCommand(light);

        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);

        remote.setSlot(lightOn);
        remote.buttonWasPressed();
        remote.setSlot(garageOpen);
        remote.buttonWasPressed();
    }
}
