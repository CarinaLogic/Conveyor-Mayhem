package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

import java.util.function.Supplier;

public class BattleUnitDisplay extends Group implements Display<Unit> {
    Supplier<Unit> unitSupplier;
    float facing = 0;
    public BattleUnitDisplay(){
    }

    public void init() {
        addActor(Game.getClient().getDisplays().get(() -> unitSupplier.get().unitParts,BattleUnitPartsDisplay.class));    }

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
        facing = (float) Math.atan2(y- unitSupplier.get().y,x- unitSupplier.get().x);
    }

    public void lookAt(Unit target){
        facing = (float) Math.atan2(target.y- unitSupplier.get().y,target.x- unitSupplier.get().x);
    }

    public float getFacing() {
        return facing;
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
