package me.carina.rpg.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

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
