package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import me.carina.rpg.Game;

public abstract class FlatImageDisplay extends ImageDisplay{
    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        setSize(getDisplayWidth(),getDisplayHeight());
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        Drawable drawable = getDrawable();
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            Game.getClient().getScreen().getCanvas().draw(transformDrawable,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),
                    getScaleX(),getScaleY(),getRotation(),getColor(),parentAlpha);
            return;
        }
        Game.getClient().getScreen().getCanvas().draw(drawable, getX(),getY(),getWidth(),getHeight(),getColor(),parentAlpha);
    }
}
