package me.carina.rpg.common.unit.newstat;

public enum StatType {
    hp(false), max_hp, sp(false), max_sp, ap(false), max_ap,attack, defence, hit, avoid;
    final boolean canMultiply;
    StatType(){
        canMultiply = true;
    }
    StatType(boolean canMultiply){
        this.canMultiply = canMultiply;
    }

    public boolean canMultiply() {
        return canMultiply;
    }
}
