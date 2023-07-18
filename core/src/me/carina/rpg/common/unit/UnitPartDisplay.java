package me.carina.rpg.common.unit;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitPartDisplay extends Display {
    UnitPart unitPart;
    public UnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
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
        return unitPart;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
