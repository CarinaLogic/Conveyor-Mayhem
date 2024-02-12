package me.carina.rpg.common.stat;

public enum StatType {
    hp(true), sp(true), mp(true), attack, defence, hit, avoid;
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
