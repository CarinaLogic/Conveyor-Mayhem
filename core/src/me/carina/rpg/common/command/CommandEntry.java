package me.carina.rpg.common.command;

import java.util.function.Predicate;

public class CommandEntry<T> {
    int index;
    CommandParser parser;
    CommandArgProvider<T> arg;
    Predicate<T> predicate;
    CommandEntry<?> parent;
    public CommandEntry(CommandParser parser, int index, CommandArgProvider<T> arg, Predicate<T> predicate, CommandEntry<?> parent){
        this.index = index;
        this.arg = arg;
        this.predicate = predicate;
        this.parser = parser;
        this.parent = parent;
    }
    public <N> CommandEntry<N> then(CommandArgProvider<N> arg, Predicate<N> predicate){
        return parser.assign(index+1,arg,predicate,this);
    }

    public boolean matches(int index, String arg, CommandEntry<?> parent){
        if (index != this.index) return false;
        if (this.parent == null && parent != null) return false;
        if (this.parent != null && parent == null) return false;
        if (this.parent != null && !this.parent.equals(parent)) return false;
        if (!this.predicate.test(this.arg.parse(arg))) return false;
        return true;
    }
}
