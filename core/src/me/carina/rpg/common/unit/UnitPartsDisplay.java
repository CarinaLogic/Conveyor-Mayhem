package me.carina.rpg.common.unit;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitPartsDisplay extends Display {
    UnitParts unitParts;
    public UnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
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
        return 1;
    }

    @Override
    public float getDisplayHeight() {
        return 1;
    }

    @Override
    public Feature getFeature() {
        return unitParts;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
