package me.carina.conveyor.common.unit;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.ArrayDisplayHandler;
import me.carina.conveyor.common.Display;

import java.util.Comparator;

public class BattleUnitPartsDisplay extends Group implements Display<UnitParts> {
    UnitParts unitParts;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> this.addActor(feature.newDisplay(BattleUnitPartDisplay.class))
    ) {
        @Override
        public UnitParts getIterable() {
            return unitParts;
        }
    };
    public BattleUnitPartsDisplay(UnitParts unitParts){
        this.unitParts = unitParts;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        unitParts.getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        handler.tick();
        for (int i = 0; i < unitParts.size(); i++) {
            unitParts.get(i).getDisplay(BattleUnitPartDisplay.class).setZIndex(i);
        }
        super.act(delta);
    }
    @Override
    public UnitParts getFeature() {
        return unitParts;
    }
}
