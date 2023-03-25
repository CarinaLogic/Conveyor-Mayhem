package me.carina.rpg.common.map;

public class ActionEffect extends AbstractAction{

    public ActionEffect(AbstractBattleActor actor) {
        super(actor);
    }

    @Override
    public boolean canActivate(AbstractBattleActor targetActor, int targetX, int targetY) {
        return false;
    }

    @Override
    public void activate(AbstractBattleActor targetActor, int targetX, int targetY) {

    }
}
