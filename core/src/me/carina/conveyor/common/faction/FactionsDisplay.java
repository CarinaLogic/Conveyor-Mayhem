package me.carina.conveyor.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.ArrayDisplayHandler;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.Feature;

public class FactionsDisplay extends Group implements Display<Factions> {
    Factions factions;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, f -> addActor(f.newDisplay(FactionDisplay.class))
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return factions;
        }
    };

    public FactionsDisplay(Factions factions){
        this.factions = factions;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }

    @Override
    public Factions getFeature() {
        return factions;
    }
}
