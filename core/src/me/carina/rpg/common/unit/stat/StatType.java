package me.carina.rpg.common.unit.stat;

public enum StatType {
    hp(true), sp(true), ap(true), attack, defence, hit, avoid;
    final boolean variable;
    StatType(){
        variable = false;
    }
    StatType(boolean variable){
        this.variable = variable;
    }

    public boolean isVariable() {
        return variable;
    }
}
