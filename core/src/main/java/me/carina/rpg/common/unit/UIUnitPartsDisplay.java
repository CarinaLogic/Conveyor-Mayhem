package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;

import java.util.Comparator;
import java.util.function.Supplier;

public class UIUnitPartsDisplay extends Stack implements Display<UnitParts> {
    Supplier<UnitParts> unitPartsSupplier;
    ArrayDisplayHandler<UnitPart,UIUnitPartDisplay> handler = new ArrayDisplayHandler<>(
            this, UIUnitPartDisplay.class, this::add
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
        handler.tick();
        UnitParts parts = unitPartsSupplier.get();
        parts.getArray().sort(Comparator.comparingInt(p -> p.bodyType.ordinal()));
        for (int i = 0; i < parts.size(); i++) {
            int finalI = i;
            Game.getClient().getDisplays().get(() -> parts.get(finalI), UIUnitPartDisplay.class).setZIndex(i);
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
