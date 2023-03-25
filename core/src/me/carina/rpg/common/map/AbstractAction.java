package me.carina.rpg.common.map;

public abstract class AbstractAction {
    AbstractBattleActor actor;
    public AbstractAction(AbstractBattleActor actor){
        this.actor = actor;
    }
    public abstract boolean canActivate(AbstractBattleActor targetActor, int targetX, int targetY);
    public abstract void activate(AbstractBattleActor targetActor, int targetX, int targetY);
}
