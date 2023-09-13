package me.carina.conveyor.common.command.commands;

import me.carina.conveyor.common.command.Command;
import me.carina.conveyor.common.command.CommandFunction;
import me.carina.conveyor.common.command.InlineCommand;

import java.util.Objects;

public class BooleanCommand extends Command {
    @CommandFunction(altNames = {"$_&&"})
    public Boolean $_and(Boolean a, InlineCommand b){
        if (!a) return false;
        return b.parse(Boolean.class);
    }
    @CommandFunction(altNames = {"$_&&"})
    public Boolean $_and(Boolean a, Boolean b){
        return a && b;
    }
    @CommandFunction(altNames = {"$_||"})
    public Boolean $_or(Boolean a, InlineCommand b){
        if (a) return true;
        return b.parse(Boolean.class);
    }
    @CommandFunction(altNames = {"$_||"})
    public Boolean $_or(Boolean a, Boolean b){
        return a || b;
    }
    @CommandFunction(altNames = {"!"})
    public Boolean not(Boolean a){
        return !a;
    }
    @CommandFunction(altNames = {"=="})
    public Boolean eq(Object a, Object b){
        return Objects.equals(a,b);
    }
    @CommandFunction(altNames = {"!="})
    public Boolean ne(Object a, Object b){
        return !Objects.equals(a,b);
    }
}
