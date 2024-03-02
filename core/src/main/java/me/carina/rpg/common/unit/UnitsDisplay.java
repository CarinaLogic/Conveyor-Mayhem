package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

import java.util.function.Supplier;

public class UnitsDisplay extends Group implements Display<Units> {
    Supplier<Units> unitsSupplier;
    ArrayDisplayHandler<Unit> handler = new ArrayDisplayHandler<>(
            this, feature -> addActor(Game.getClient().getDisplays().get(()->feature,BattleUnitDisplay.class))
    ) {
        @Override
        public Units getIterable() {
            return unitsSupplier.get();
        }
    };
    public UnitsDisplay() {
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
    public Supplier<Units> getFeatureSupplier() {
        return unitsSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Units> supplier) {
        this.unitsSupplier = supplier;
    }
}
