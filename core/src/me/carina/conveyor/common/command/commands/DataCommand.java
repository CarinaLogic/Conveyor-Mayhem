package me.carina.conveyor.common.command.commands;

import me.carina.conveyor.common.command.Command;
import me.carina.conveyor.common.command.CommandData;
import me.carina.conveyor.common.command.CommandFunction;

public class DataCommand extends Command {
    @CommandFunction(altNames = "$_=")
    public Object store(CommandData data, Object o){
        data.setValue(o);
        return o;
    }
}
