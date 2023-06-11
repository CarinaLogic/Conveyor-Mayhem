package me.carina.rpg.common.world.action;

import me.carina.rpg.common.world.unit.BattleActor;

public class ActionEffect extends AbstractAction {

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
