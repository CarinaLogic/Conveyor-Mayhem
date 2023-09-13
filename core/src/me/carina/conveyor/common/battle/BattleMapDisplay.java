package me.carina.conveyor.common.battle;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.conveyor.Game;
import me.carina.conveyor.client.misc.Movable;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.faction.FactionsDisplay;

public class BattleMapDisplay extends Stack implements Display<BattleMap> {
    BattleMap map;
    public BattleMapDisplay(BattleMap map){
        this.map = map;
        addActor(map.tiles.newDisplay(TilesDisplay.class));
        addActor(map.factions.newDisplay(FactionsDisplay.class));
        setSize(map.getWidth(), map.getHeight());
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }


    @Override
    public BattleMap getFeature() {
        return map;
    }
}
