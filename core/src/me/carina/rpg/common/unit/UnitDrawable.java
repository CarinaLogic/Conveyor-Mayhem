package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import me.carina.rpg.common.util.StackedDrawable;

public class UnitDrawable extends StackedDrawable {
    public UnitDrawable(TransformDrawable... drawables) {
        super(drawables);
    }

    public UnitDrawable(TextureRegion... textureRegions) {
        super(textureRegions);
    }
}
