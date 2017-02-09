package patterns.command.commands;

public class MacroCommand extends Command {

    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Command command : commands) {
            res.append(command);
            res.append(" + ");
        }
        return res.toString();
    }
}
