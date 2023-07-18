package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitDisplay extends Display {
    Unit unit;
    public UnitDisplay(Unit unit){
        this.unit = unit;
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
        return 0;
    }

    @Override
    public float getDisplayHeight() {
        return 0;
    }

    @Override
    public Feature getFeature() {
        return unit;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
