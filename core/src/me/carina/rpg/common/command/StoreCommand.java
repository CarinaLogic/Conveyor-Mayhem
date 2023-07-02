package me.carina.rpg.common.command;

public class StoreCommand implements Command{
    @Override
    public String getPrefix() {
        return "store";
    }

    @Override
    public void run(CommandParser parser, String... args) {
        parser.setValue(args[0], args[1]);
    }
}
