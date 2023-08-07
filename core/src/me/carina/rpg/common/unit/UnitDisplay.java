package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.FlatImageDisplay;

public class UnitDisplay extends Display {
    Unit unit;
    public UnitDisplay(Unit unit){
        this.unit = unit;
    }

    @Override
    public void tick() {

    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        //NOOP
    }

    @Override
    public float getDisplayX() {
        return unit.x + 0.5f;
    }

    @Override
    public float getDisplayY() {
        return unit.y + 0.5f;
    }

    @Override
    public float getDisplayWidth() {
        return 1;
    }

    @Override
    public float getDisplayHeight() {
        return 1;
    }

    @Override
    public Feature getFeature() {
        return unit;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }

    @Override
    public int getAlignment() {
        return Align.bottom;
    }
}
