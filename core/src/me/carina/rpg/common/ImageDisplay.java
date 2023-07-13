package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.Path;

public abstract class ImageDisplay extends Display{

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setSize(getFeature().getDisplayWidth(),getFeature().getDisplayHeight());
        setPosition(getFeature().getDisplayX(),getFeature().getDisplayY());
        Drawable drawable = getFeature().getDrawable();
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            transformDrawable.draw(batch,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),
                    getScaleX(),getScaleY(),getRotation());
            return;
        }
        drawable.draw(batch, getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public abstract DrawableFeature getFeature();

}
