package me.carina.rpg.common.unit;

import me.carina.rpg.common.ArrayDisplay;
import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitPartsDisplay extends ArrayDisplay<UnitPart> {
    UnitParts unitParts;
    public UnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
    }

    @Override
    public void tickMore() {

    }

    @Override
    public ArrayFeature<UnitPart> getFeature() {
        return unitParts;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
