package me.carina.rpg.common.command;

public class IntNode extends BasicArgNode<Integer>{

    @Override
    public boolean canCast(String s) {
        try {
            Integer.valueOf(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }

    }

    @Override
    public Integer get(String s) {
        return Integer.valueOf(s);
    }

    @Override
    public boolean isApplicable(Integer obj) {
        return true;
    }
}
