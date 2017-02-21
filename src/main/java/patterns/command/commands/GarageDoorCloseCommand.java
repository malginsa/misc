package patterns.command.commands;

import patterns.command.devices.GarageDoor;

public class GarageDoorCloseCommand extends Command {

    GarageDoor garageDoor;

    public GarageDoorCloseCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.checkObstacles();
        garageDoor.down();
    }
}
