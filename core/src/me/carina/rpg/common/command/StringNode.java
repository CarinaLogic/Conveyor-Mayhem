package me.carina.rpg.common.command;

public class StringNode extends BasicArgNode<String>{
    @Override
    public boolean canCast(String s) {
        return true;
    }

    @Override
    public String get(String s) {
        return s;
    }

    @Override
    public boolean isApplicable(String obj) {
        return true;
    }
}
