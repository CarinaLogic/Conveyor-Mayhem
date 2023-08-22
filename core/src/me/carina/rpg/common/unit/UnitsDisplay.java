package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitsDisplay extends Group implements Display<Units> {
    Units units;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> addActor(feature.newDisplay(UnitDisplay.class))
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return units;
        }
    };
    public UnitsDisplay(Units units) {
        this.units = units;
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
