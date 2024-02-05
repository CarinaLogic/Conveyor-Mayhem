package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

public class BattleUnitDisplay extends Group implements Display<Unit> {
    Unit unit;
    float facing = 0;
    public BattleUnitDisplay(Unit unit){
        this.unit = unit;
        addActor(unit.unitParts.newDisplay(BattleUnitPartsDisplay.class));
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void lookAt(float x, float y){
        facing = (float) Math.atan2(y- unit.y,x- unit.x);
    }

    public void lookAt(Unit target){
        facing = (float) Math.atan2(target.y- unit.y,target.x- unit.x);
    }

    public float getFacing() {
        return facing;
    }

    @Override
    public Unit getFeature() {
        return unit;
    }
}
