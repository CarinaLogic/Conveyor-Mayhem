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
    float facing = 0;
    public UnitDisplay(Unit unit){
        this.unit = unit;
    }

    public void lookAt(float x, float y){
        facing = (float) Math.atan2(y- unit.y,x- unit.x);
    }

    public void lookAt(Unit target){
        facing = (float) Math.atan2(target.y- unit.y,target.x- unit.x);
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
    public Unit getFeature() {
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

    public float getFacing() {
        return facing;
    }
}
