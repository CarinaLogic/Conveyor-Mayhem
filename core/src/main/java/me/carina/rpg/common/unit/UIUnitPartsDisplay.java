package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;

import java.util.Comparator;
import java.util.function.Supplier;

public class UIUnitPartsDisplay extends Stack implements Display<UnitParts> {
    Supplier<UnitParts> unitPartsSupplier;
    ArrayDisplayHandler<UnitPart> handler = new ArrayDisplayHandler<>(
            this, feature -> this.add(Game.getClient().getDisplays().get(()->feature,UIUnitPartDisplay.class))
    ) {
        @Override
        public UnitParts getIterable() {
            return unitPartsSupplier.get();
        }
    };
    public UIUnitPartsDisplay(){
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        unitPartsSupplier.get().getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        handler.tick();
        for (int i = 0; i < unitPartsSupplier.get().size(); i++) {
            int finalI = i;
            Game.getClient().getDisplays().get(()->unitPartsSupplier.get().get(finalI), UIUnitPartDisplay.class).setZIndex(i);
        }
        super.act(delta);
    }

    @Override
    public Supplier<UnitParts> getFeatureSupplier() {
        return unitPartsSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<UnitParts> supplier) {
        this.unitPartsSupplier = supplier;
    }
}
