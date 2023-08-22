package me.carina.rpg.common.world.map;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.ImageDisplay;

public class FactionDisplay extends ImageDisplay implements Display<Faction> {
    Faction faction;
    public FactionDisplay(Faction faction){
        this.faction = faction;
    }

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return 0;
    }

    @Override
    public float getDisplayHeight() {
        return 0;
    }

    @Override
    public Drawable getDrawable() {
        return null;
    }

    @Override
    public Faction getFeature() {
        return faction;
    }
}
