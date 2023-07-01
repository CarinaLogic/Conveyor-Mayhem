package me.carina.rpg.common.command;

import me.carina.rpg.common.util.Array;

public abstract class CommandNode<T> {
    Array<CommandNode<?>> nextNodes = new Array<>();
    public abstract boolean consumes();
    public abstract boolean canCast(String s);
    public abstract T get(String s);
    public abstract boolean isApplicable(T obj);

    public abstract boolean isCompleted();
    public abstract void run();
    public void addNode(CommandNode<?>... nodes){
        nextNodes.addAll(nodes);
    }
}
