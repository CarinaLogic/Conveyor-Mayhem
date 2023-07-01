package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public class Command {
    Array<Class<? extends Command>> nextCmdClass = new Array<>();
    public Command(){
        next();
    }

    public void next(){
        nextCmdClass.add(SayCommand.class);
    }
    public int getConsumption(){
        return -1;
    }
    public void init(String... args){

    }
    public void run(){

    }
}
