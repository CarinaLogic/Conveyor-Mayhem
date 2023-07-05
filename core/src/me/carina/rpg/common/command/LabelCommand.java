package me.carina.rpg.common.command;

public class LabelCommand implements Command{
    @Override
    public String getPrefix() {
        return "label";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        parser.labels.put(args[0], parser.cursor);
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        return true;
    }
}
