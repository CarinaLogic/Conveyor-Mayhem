package me.carina.rpg.common.map;

public abstract class AbstractAction {
    BattleActor actor;
    public AbstractAction(BattleActor actor){
        this.actor = actor;
    }
    public abstract boolean canActivate(BattleActor targetActor, int targetX, int targetY);
    public abstract void activate(BattleActor targetActor, int targetX, int targetY);
}
