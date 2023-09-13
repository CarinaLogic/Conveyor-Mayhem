package me.carina.conveyor.common.command.commands;

import me.carina.conveyor.Game;
import me.carina.conveyor.common.command.Command;
import me.carina.conveyor.common.command.CommandFunction;

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
