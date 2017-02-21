package patterns.command.commands;

import patterns.command.devices.Stereo;

public class StereoOffWithCDCommand extends Command {

    Stereo stereo;

    public StereoOffWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.ejectCD();
        stereo.off();
    }
}
