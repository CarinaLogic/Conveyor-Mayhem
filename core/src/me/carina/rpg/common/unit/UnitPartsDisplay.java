package me.carina.rpg.common.unit;

import me.carina.rpg.common.ArrayDisplay;
import me.carina.rpg.common.ArrayFeature;

import java.util.Comparator;

public class UnitPartsDisplay extends ArrayDisplay<UnitPart> {
    UnitParts unitParts;
    public UnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
    }

    @Override
    public void tick() {
        unitParts.getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        super.tick();
        for (int i = 0; i < unitParts.size(); i++) {
            if (unitParts.getDisplay() != null) unitParts.get(i).getDisplay().setZIndex(i);
        }
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
