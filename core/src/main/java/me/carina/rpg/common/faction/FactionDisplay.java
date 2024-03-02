package me.carina.rpg.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.UnitsDisplay;

import java.util.function.Supplier;

public class FactionDisplay extends Group implements Display<Faction> {
    Supplier<Faction> factionSupplier;
    public FactionDisplay(){
        addActor(Game.getClient().getDisplays().get(()->factionSupplier.get().getUnits(),UnitsDisplay.class));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public Supplier<Faction> getFeatureSupplier() {
        return factionSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Faction> supplier) {
        this.factionSupplier = supplier;
    }
}
