package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

@SuppressWarnings("WrapperTypeMayBePrimitive")
public class ArrayCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
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
