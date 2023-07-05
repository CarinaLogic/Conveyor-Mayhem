package me.carina.rpg.common.command;

public class JumpCommand implements Command{
    @Override
    public String getPrefix() {
        return "jump";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        parser.cursor = parser.labels.get(args[0]);
        return true;
    }
}
