package me.carina.conveyor.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.unit.UnitsDisplay;

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
