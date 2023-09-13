package me.carina.conveyor.common.command.commands;

import me.carina.conveyor.common.command.Command;
import me.carina.conveyor.common.command.CommandFunction;

public class WaitCommand extends Command {
    @CommandFunction
    public void wait_$(Double seconds){
        getScript().setWaitTime((float) (getScript().getWaitTime()+seconds));
    }
}
