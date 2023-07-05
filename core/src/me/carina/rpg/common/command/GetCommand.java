package me.carina.rpg.common.command;

public class GetCommand implements Command{
    @Override
    public String getPrefix() {
        return "get";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        return true;
    }
}
