package me.carina.rpg.common.command;

import me.carina.rpg.Game;

public class GetCommand implements Command{
    @Override
    public String getPrefix() {
        return "get";
    }

    @Override
    public void run(CommandParser parser, String... args) {
        Game.getInstance().getLogger().info(parser.getValue(args[0]).toString());
    }
}
