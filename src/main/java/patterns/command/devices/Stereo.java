package patterns.command.devices;

public class Stereo {

    public void on() {
        System.out.println("Stereo is on");
    }

    public void setCD() {
        System.out.println("CD is inserted in Stereo");
    }

    public void setVolume(int volume) {
        System.out.println("Volume of Stereo is " + volume);
    }

    public void off() {
        System.out.println("Stereo is off");
    }

    public void ejectCD() {
        System.out.println("CD is ejected");
    }
}
