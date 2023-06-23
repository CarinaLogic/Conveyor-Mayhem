package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

import java.util.function.Predicate;

public class CommandParser {
    Array<CommandEntry<?>> entries;
    public <T> CommandEntry<T> assign(int index, CommandArgProvider<T> arg,
                                      Predicate<T> predicate, CommandEntry<?> parent){
        CommandEntry<T> entry = new CommandEntry<>(this,index,arg,predicate,parent);
        entries.add(entry);
        return entry;
    }
    public CommandEntry<Command> assign(String commandType){
        return assign(0,new CommandProvider(),,null);
    }
    public void parse(String s){
        String[] args = s.split(" ");
        CommandEntry<?> prevParent = null;
        for (int i = 0; i < args.length; i++) {
            //Variables in lambda must be final
            int fi = i;
            CommandEntry<?> fPrevParent = prevParent;
            CommandEntry<?> entry = entries.firstMatch(e -> e.matches(fi, args[fi], fPrevParent));
            prevParent = entry;
        }
    }
}
