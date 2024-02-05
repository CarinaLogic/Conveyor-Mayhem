package me.carina.rpg.common.command.commands;

import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandFunction;

public class WaitCommand extends Command {
    @CommandFunction
    public void wait_$(Double seconds){
        getScript().setWaitTime((float) (getScript().getWaitTime()+seconds));
    }
}
