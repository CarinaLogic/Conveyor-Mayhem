package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

public class UIUnitDisplay extends Stack implements Display<Unit> {
    Unit unit;
    boolean flip;
    public UIUnitDisplay(Unit unit){
        this.unit = unit;
        addActor(unit.unitParts.newDisplay(UIUnitPartsDisplay.class));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        super.draw(batch, parentAlpha);
    }

    @Override
    public Unit getFeature() {
        return unit;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }
}
