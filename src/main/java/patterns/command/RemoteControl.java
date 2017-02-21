package patterns.command;

import patterns.command.commands.Command;
import patterns.command.commands.NoCommand;

public class RemoteControl {

    public static final int SLOTS_COUNT = 6;

    Command[] onCommands;
    Command[] offCommands;

    public RemoteControl() {
        onCommands = new Command[SLOTS_COUNT];
        offCommands = new Command[SLOTS_COUNT];

        Command noCommand = new NoCommand();
        for (int i = 0; i < SLOTS_COUNT; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
//            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getSimpleName() + "    " + offCommands[i].getClass().getSimpleName() + "\n");
            stringBuff.append("[slot " + i + "] " + onCommands[i] + "    " + offCommands[i] + "\n");
        }
        return stringBuff.toString();
    }
}
