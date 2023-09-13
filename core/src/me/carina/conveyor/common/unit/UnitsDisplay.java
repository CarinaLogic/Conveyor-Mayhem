package me.carina.conveyor.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.ArrayDisplayHandler;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.Feature;

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
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
