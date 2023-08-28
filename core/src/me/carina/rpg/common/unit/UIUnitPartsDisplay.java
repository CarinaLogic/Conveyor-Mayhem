package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;

import java.util.Comparator;

public class UIUnitPartsDisplay extends Stack implements Display<UnitParts> {
    UnitParts unitParts;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> this.add(feature.newDisplay(UIUnitPartDisplay.class))
    ) {
        @Override
        public UnitParts getIterable() {
            return unitParts;
        }
    };
    public UIUnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        unitParts.getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        handler.tick();
        for (int i = 0; i < unitParts.size(); i++) {
            unitParts.get(i).getDisplay(UIUnitPartDisplay.class).setZIndex(i);
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public UnitParts getFeature() {
        return unitParts;
    }
}
