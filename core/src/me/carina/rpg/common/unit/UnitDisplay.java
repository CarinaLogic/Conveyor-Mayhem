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
    public Feature getFeature() {
        return unit;
    }
}
