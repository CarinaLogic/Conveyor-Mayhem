package me.carina.rpg.common.unit;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitPartDisplay extends Display {
    UnitPart unitPart;
    public UnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
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
