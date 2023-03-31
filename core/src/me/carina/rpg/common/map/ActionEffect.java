package me.carina.rpg.common.map;

public class ActionEffect extends AbstractAction{

    public ActionEffect(BattleActor actor) {
        super(actor);
    }

    @Override
    public boolean canActivate(BattleActor targetActor, int targetX, int targetY) {
        return false;
    }

    @Override
    public void activate(BattleActor targetActor, int targetX, int targetY) {

    }
}
