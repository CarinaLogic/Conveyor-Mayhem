package me.carina.rpg.common.command;

public abstract class BasicArgNode<T> extends CommandNode<T>{
    @Override
    public boolean consumes() {
        return true;
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
