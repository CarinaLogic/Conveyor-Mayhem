package me.carina.rpg.common.stat;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.ImageDisplay;

public class AffinityIconDisplay extends ImageDisplay implements Display<Affinity> {
    Affinity affinity;
    public AffinityIconDisplay(Affinity affinity){

    }
    @Override
    public Affinity getFeature() {
        return null;
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
}
