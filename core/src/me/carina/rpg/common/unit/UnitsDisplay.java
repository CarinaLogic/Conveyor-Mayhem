package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class UnitsDisplay extends Group implements Display<Units> {
    Units units;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> addActor(feature.newDisplay(BattleUnitDisplay.class))
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
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.draw(batch, parentAlpha);
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
