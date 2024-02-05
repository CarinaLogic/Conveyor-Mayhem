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
    @CommandFunction(altNames = {"$_+=","$_<-"})
    public <T> Array<T> array_add(Array<T> array, T obj){
        array.add(obj);
        return array;
    }
    @CommandFunction
    public <T> Array<T> array_addfirst(Array<T> array, T obj){
        array.add(obj);
        return array;
    }
    @CommandFunction(altNames = {"$_->"},suppressOriginalName = true)
    public <T> Array<T> array_addfirst(T obj, Array<T> array){
        array.insert(0,obj);
        return array;
    }
    @CommandFunction(altNames = {"$_->"})
    public <T> Array<T> array_remove(Array<T> array, T obj){
        //noinspection unchecked
        array.remove(obj);
        return array;
    }
    @CommandFunction(altNames = {"<-_$"})
    public <T> Array<T> array_removefirst(Array<T> array){
        array.removeIndex(0);
        return array;
    }
    @CommandFunction(altNames = {"$_->"})
    public <T> Array<T> array_removelast(Array<T> array){
        array.removeIndex(array.size-1);
        return array;
    }
    @CommandFunction
    public <T> Double array_size(Array<T> array){
        return (double) array.size;
    }
}
