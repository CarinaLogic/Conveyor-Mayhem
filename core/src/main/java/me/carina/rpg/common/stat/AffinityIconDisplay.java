package me.carina.rpg.common.stat;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.ImageDisplay;

import java.util.function.Supplier;

public class AffinityIconDisplay extends ImageDisplay implements Display<Affinity> {
    Supplier<Affinity> affinitySupplier;
    public AffinityIconDisplay(Supplier<Affinity> affinitySupplier){
        this.affinitySupplier = affinitySupplier;
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
        return 16;
    }

    @Override
    public float getDisplayHeight() {
        return 16;
    }

    @Override
    public Drawable getDrawable() {
        return Game.getInstance().getAssets().get(getFeature().getPath(),Drawable.class);
    }

    @Override
    public Supplier<Affinity> getFeatureSupplier() {
        return affinitySupplier;
    }
}
