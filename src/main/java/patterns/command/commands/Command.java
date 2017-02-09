package patterns.command.commands;

public abstract class Command {

    public abstract void execute();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
