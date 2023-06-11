package me.carina.rpg.common.world.map;

public enum Element {
    ;
    final boolean isAttack;
    final boolean isDefence;
    Element(boolean isAttack, boolean isDefence){
        this.isAttack = isAttack;
        this.isDefence = isDefence;
    }
}
