package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import me.carina.rpg.Game;

public abstract class FlatImageDisplay extends ImageDisplay{
    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        tick();
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        setSize(getDisplayWidth(),getDisplayHeight());
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            Game.getClient().getScreen().getCanvas().draw(transformDrawable,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),
                    getScaleX(),getScaleY(),getRotation(),getColor(),parentAlpha);
            return;
        }
        Game.getClient().getScreen().getCanvas().draw(drawable, getX(),getY(),getWidth(),getHeight(),getColor(),parentAlpha);
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        if (!getDebug()) return;
        shapes.end();
        Matrix4 proj = shapes.getProjectionMatrix().cpy();
        Matrix4 tran = shapes.getTransformMatrix().cpy();
        shapes.setProjectionMatrix(Game.getClient().getScreen().getCanvas().getCamera().combined.cpy());
        shapes.updateMatrices();
        shapes.begin();
        shapes.set(ShapeRenderer.ShapeType.Line);
        if (getStage() != null) shapes.setColor(getStage().getDebugColor());
        shapes.rect(getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
        shapes.end();
        shapes.setProjectionMatrix(proj.cpy());
        shapes.setTransformMatrix(tran);
        shapes.updateMatrices();
        shapes.begin();
    }
}
