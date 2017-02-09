package patterns.command.commands;

import patterns.command.devices.Light;

public class LightOffCommand extends Command {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
