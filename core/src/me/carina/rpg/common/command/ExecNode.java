package me.carina.rpg.common.command;

public class ExecNode extends CommandNode<Object>{
    @Override
    public boolean consumes() {
        return false;
    }

    @Override
    public boolean canCast(String s) {
        return false;
    }

    @Override
    public Object get(String s) {
        return null;
    }

    @Override
    public boolean isApplicable(Object obj) {
        return false;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
