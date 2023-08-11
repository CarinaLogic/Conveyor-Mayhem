package me.carina.rpg.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public abstract class ImageDisplay extends Display{
    boolean flipX = false;
    boolean flipY = false;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        tick();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        setSize(getDisplayWidth(),getDisplayHeight());
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        float x = getX();
        float y = getY();
        float w = getWidth();
        float h = getHeight();
        if (flipX){
            x += w;
            w = -w;
        }
        if (flipY){
            y += h;
            h = -h;
        }
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            transformDrawable.draw(batch,x,y,getOriginX(),getOriginY(),w,h,
                    getScaleX(),getScaleY(),getRotation());
            return;
        }
        drawable.draw(batch, x,y,w,h);
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public abstract Drawable getDrawable();
}
