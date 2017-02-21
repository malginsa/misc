package patterns.command;

import patterns.command.commands.*;
import patterns.command.devices.*;

public class Client {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoom = new Light("Living room");
        remoteControl.setCommand(0,
                new LightOnCommand(livingRoom),
                new LightOffCommand(livingRoom));

        Light kitchen = new Light("Kitchen");
        remoteControl.setCommand(1,
                new LightOnCommand(kitchen),
                new LightOffCommand(kitchen));

        Stereo stereo = new Stereo();
        remoteControl.setCommand(2,
                new StereoOnWithCDCommand(stereo),
                new StereoOffWithCDCommand(stereo));

        GarageDoor garageDoor = new GarageDoor();
        remoteControl.setCommand(3,
                new GarageDoorOpenCommand(garageDoor),
                new GarageDoorCloseCommand(garageDoor));

        // batch of commands
        MacroCommand macroOnCommand = new MacroCommand(new Command[]{
                new LightOnCommand(livingRoom),
                new LightOnCommand(kitchen),
                new StereoOnWithCDCommand(stereo)});
        MacroCommand macroOffCommand = new MacroCommand(new Command[]{
                new LightOffCommand(livingRoom),
                new LightOffCommand(kitchen),
                new StereoOffWithCDCommand(stereo)});
        remoteControl.setCommand(4, macroOnCommand, macroOffCommand);

        System.out.println(remoteControl);

        for (int i = 0; i < RemoteControl.SLOTS_COUNT; i++) {
            remoteControl.onButtonWasPushed(i);
            remoteControl.offButtonWasPushed(i);
        }
    }
}
