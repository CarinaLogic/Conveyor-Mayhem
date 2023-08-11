package me.carina.rpg.common.command;

import me.carina.rpg.Game;
import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.common.unit.Unit;

public class BattleAnimationCommand extends Command{
    @Override
    public boolean enabled() {
        if (!Game.getInstance().isClient()) return false;
        return Game.getClient().getScreen(BaseScreen.class) != null;
    }
}
