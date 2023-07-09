package me.carina.rpg.common.world.map;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class FactionDisplay extends Display {
    Faction faction;
    public FactionDisplay(Faction faction){
        this.faction = faction;
    }

    @Override
    public Feature getFeature() {
        return faction;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
