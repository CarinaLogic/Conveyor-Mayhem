package me.carina.rpg.common.command;

public class SetCommand implements Command{
    @Override
    public String getPrefix() {
        return "set";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        parser.setValue(args[0], parser.parseString(args[1]));
        return true;
    }
}
