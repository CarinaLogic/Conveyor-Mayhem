package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

import java.util.function.Supplier;

public class UIUnitDisplay extends Stack implements Display<Unit> {
    Supplier<Unit> unitSupplier = ()->null;
    boolean flip;
    public UIUnitDisplay(){
    }

    public void init() {
        add(Game.getClient().getDisplays().get(()-> unitSupplier.get() == null ? null : unitSupplier.get().unitParts,UIUnitPartsDisplay.class));
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }


    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    @Override
    public Supplier<Unit> getFeatureSupplier() {
        return unitSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Unit> supplier) {
        this.unitSupplier = supplier;
    }
}
