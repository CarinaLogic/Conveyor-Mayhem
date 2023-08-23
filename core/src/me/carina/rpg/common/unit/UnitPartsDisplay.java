package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Display;

import java.util.Comparator;

public class UnitPartsDisplay extends Group implements Display<UnitParts> {
    UnitParts unitParts;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> this.addActor(feature.newDisplay(UnitPartDisplay.class))
    ) {
        @Override
        public UnitParts getIterable() {
            return unitParts;
        }
    };
    public UnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        unitParts.getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        for (int i = 0; i < unitParts.size(); i++) {
            if (unitParts.getDisplay(UnitPartDisplay.class) != null) unitParts.get(i).getDisplay(UnitPartDisplay.class).setZIndex(i);
        }
        super.draw(batch, parentAlpha);
    }

    @Override
    public UnitParts getFeature() {
        return unitParts;
    }
}
