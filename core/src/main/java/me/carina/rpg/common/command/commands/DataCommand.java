package me.carina.rpg.common.command.commands;

import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandData;
import me.carina.rpg.common.command.CommandFunction;

public class DataCommand extends Command {
    @CommandFunction(altNames = "$_=")
    public Object store(CommandData data, Object o){
        data.setValue(o);
        return o;
    }
}
