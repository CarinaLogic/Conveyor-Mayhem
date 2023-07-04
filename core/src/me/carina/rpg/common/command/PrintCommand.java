package me.carina.rpg.common.command;

import me.carina.rpg.Game;

public class PrintCommand implements Command{
    @Override
    public String getPrefix() {
        return "print";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        Game.getInstance().getLogger().info(parser.getValue(args[0]).toString());
        return true;
    }
}
