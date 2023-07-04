package me.carina.rpg.common.command;

import me.carina.rpg.Game;

public class SayCommand implements Command{
    @Override
    public String getPrefix() {
        return "say";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser,String... args) {
        StringBuilder msg = new StringBuilder();
        for (String arg : args) {
            msg.append(arg);
            msg.append(" ");
        }
        Game.getInstance().getLogger().info(msg.toString().trim());
        return true;
    }
}
