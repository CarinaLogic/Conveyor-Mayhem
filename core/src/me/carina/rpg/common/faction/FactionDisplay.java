package me.carina.rpg.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.UnitsDisplay;

public class FactionDisplay extends Group implements Display<Faction> {
    Faction faction;
    public FactionDisplay(Faction faction){
        this.faction = faction;
        addActor(faction.getUnits().newDisplay(UnitsDisplay.class));
    }
    @Override
    public Faction getFeature() {
        return faction;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
