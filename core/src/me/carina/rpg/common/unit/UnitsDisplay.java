package me.carina.rpg.common.unit;

import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitsDisplay extends Display {
    Units units;
    public UnitsDisplay(Units units){
        this.units = units;
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
        return units;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
