package patterns.command.commands;

import patterns.command.commands.Command;
import patterns.command.devices.GarageDoor;

public class GarageDoorOpenCommand extends Command {

    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
