package me.carina.rpg.common.command;

public class LiteralNode extends BasicArgNode<String> {
    String key;
    public LiteralNode(String key){
        this.key = key;
    }


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
        return this.key.equals(obj);
    }
}
