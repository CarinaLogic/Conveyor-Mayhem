package me.carina.rpg.common.command.commands;

import me.carina.rpg.Game;
import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandFunction;

public class IOCommand extends Command {
    @CommandFunction
    public void print(Object o){
        Game.getInstance().getLogger().info(o.toString());
    }
    @CommandFunction
    public void wait_$(Double seconds){
        getScript().setWaitTime((float) (getScript().getWaitTime()+seconds));
    }
}
