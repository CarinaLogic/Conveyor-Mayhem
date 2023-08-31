package me.carina.rpg.common.command.commands;

import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandData;
import me.carina.rpg.common.command.CommandException;
import me.carina.rpg.common.command.CommandFunction;

public class MathCommand extends Command {
    @CommandFunction(altNames = {"$_+"})
    public Double add(Double a, Double b){
        return a + b;
    }
    @CommandFunction(altNames = {"$_-"})
    public Double sub(Double a, Double b){
        return a - b;
    }
    @CommandFunction(altNames = {"$_*"})
    public Double mul(Double a, Double b){
        return a * b;
    }
    @CommandFunction(altNames = {"$_/"})
    public Double div(Double a, Double b){
        if (b == 0) throw new CommandException(CommandException.ExceptionType.zero_division);
        return a / b;
    }
    @CommandFunction(altNames = {"$_+="})
    public Double addBy(CommandData data, Double v){
        double d = data.getValue(Double.class)+v;
        data.setValue(d);
        return d;
    }
    @CommandFunction(altNames = {"$_-="})
    public Double subBy(CommandData data, Double v){
        double d = data.getValue(Double.class)-v;
        data.setValue(d);
        return d;
    }
    @CommandFunction(altNames = {"$_*="})
    public Double mulBy(CommandData data, Double v){
        double d = data.getValue(Double.class)*v;
        data.setValue(d);
        return d;
    }
    @CommandFunction(altNames = {"$_/="})
    public Double divBy(CommandData data, Double v){
        if (v == 0) throw new CommandException(CommandException.ExceptionType.zero_division);
        double d = data.getValue(Double.class)/v;
        data.setValue(d);
        return d;
    }
    @CommandFunction(altNames = {"$_<"})
    public boolean lt(Double a, Double b){
        return a < b;
    }
    @CommandFunction(altNames = {"$_<="})
    public boolean le(Double a, Double b){
        return a <= b;
    }
    @CommandFunction(altNames = {"$_>"})
    public boolean gt(Double a, Double b){
        return a > b;
    }
    @CommandFunction(altNames = {"$_>="})
    public boolean ge(Double a, Double b){
        return a >= b;
    }
    @CommandFunction(altNames = {"$_%"})
    public Double mod(Double a, Double b){
        return a % b;
    }
}
