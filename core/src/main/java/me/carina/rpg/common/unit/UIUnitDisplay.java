package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

public class UIUnitDisplay extends Stack implements Display<Unit> {
    Unit unit;
    boolean flip;
    public UIUnitDisplay(Unit unit){
        this.unit = unit;
        add(unit.unitParts.newDisplay(UIUnitPartsDisplay.class));
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }


    @Override
    public Unit getFeature() {
        return unit;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }
}
