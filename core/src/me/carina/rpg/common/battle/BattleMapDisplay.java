package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import me.carina.rpg.client.misc.Movable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.util.Array2D;

public class BattleMapDisplay extends Display {
    BattleMap map;
    public BattleMapDisplay(BattleMap map){
        this.map = map;
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return map.getWidth();
    }

    @Override
    public float getDisplayHeight() {
        return map.getHeight();
    }

    @Override
    public Feature getFeature() {
        return map;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
