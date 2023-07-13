package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class DrawableFeature extends Feature{
    //Fuck the optimization
    public abstract Drawable getDrawable();
}
