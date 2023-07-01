package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public class CommandArgs {
    Array<Object> args;
    public int getInt(int index){
        return (Integer) args.get(index);
    }
    public String getString(int index){
        return (String) args.get(index);
    }
}
