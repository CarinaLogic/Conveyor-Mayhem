package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.Movable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.UnitsDisplay;

public class BattleMapDisplay extends Stack implements Display<BattleMap> {
    BattleMap map;
    public BattleMapDisplay(BattleMap map){
        this.map = map;
        addActor(map.tiles.newDisplay(TilesDisplay.class));
        addActor(map.units.newDisplay(UnitsDisplay.class));
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
