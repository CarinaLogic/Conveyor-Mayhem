package me.carina.rpg.common.command.commands;

import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.command.CommandFunction;
import me.carina.rpg.common.util.Array;

public class ArrayCommand extends Command {
    @CommandFunction
    public Array<Double> range(Double i){
        Array<Double> a = new Array<>();
        for (Double j = 0.0; j < i; j++) {
            a.add(j);
        }
        return a;
    }
    @CommandFunction
    public Array<Double> range(Double begin, Double end){
        Array<Double> a = new Array<>();
        for (Double i = begin; i < end; i++) {
             a.add(i);
        }
        return a;
    }
    @CommandFunction
    public Array<Double> range(Double begin, Double end, Double increment){
        Array<Double> a = new Array<>();
        for (Double i = begin; i < end; i += increment) {
            a.add(i);
        }
        return a;
    }
}
