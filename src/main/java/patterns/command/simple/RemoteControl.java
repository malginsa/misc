package patterns.command.simple;

import patterns.command.commands.Command;

public class RemoteControl {

    private Command slot;

    public RemoteControl() {
    }

    public void setSlot(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
