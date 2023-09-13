package me.carina.conveyor.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;
import me.carina.conveyor.Game;

public abstract class FlatImageDisplay extends ImageDisplay{
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        setSize(getDisplayWidth(),getDisplayHeight());
        Vector3 camAim = getStage().getCamera().direction;
        setOrigin(getAlignment());
        float originalRot = getRotation();
        setRotation((float) Math.toDegrees(Math.atan2(camAim.y,camAim.x))-90f);
        float alignY = 0;
        if (Align.isCenterVertical(getAlignment())) alignY += getDisplayHeight()/2;
        if (Align.isTop(getAlignment())) alignY += getDisplayHeight();
        Vector2 l = new Vector2(0,alignY);
        Vector2 r = new Vector2(getDisplayWidth(),alignY);
        localToScreenCoordinates(l);
        localToScreenCoordinates(r);
        float sw = r.cpy().sub(l).x;
        float sh = sw * getDisplayHeight() / getDisplayWidth();
        float sx = l.x;
        float sy = l.y - sh * alignY / getDisplayHeight();
        if (flipX){
            sx += sw;
            sw = -sw;
        }
        if (flipY){
            sy += sh;
            sh = -sh;
        }
        setRotation(originalRot);
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            Game.getClient().getScreen().getCanvas().draw(transformDrawable,sx,sy,getOriginX(),getOriginY(),sw,sh,
                    getScaleX(),getScaleY(),getRotation(),getColor(),parentAlpha);
            return;
        }
        Game.getClient().getScreen().getCanvas().draw(drawable, sx,sy,sw,sh,getColor(),parentAlpha);
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        if (!getDebug()) return;
        shapes.flush();
        Matrix4 proj = shapes.getProjectionMatrix().cpy();
        shapes.setProjectionMatrix(Game.getClient().getScreen().getCanvas().getCamera().combined.cpy());
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        setSize(getDisplayWidth(),getDisplayHeight());
        Vector3 camAim = getStage().getCamera().direction;
        setOrigin(getAlignment());
        float originalRot = getRotation();
        setRotation((float) Math.toDegrees(Math.atan2(camAim.y,camAim.x))-90f);
        float alignY = 0;
        if (Align.isCenterVertical(getAlignment())) alignY += getDisplayHeight()/2;
        if (Align.isTop(getAlignment())) alignY += getDisplayHeight();
        Vector2 l = new Vector2(0,alignY);
        Vector2 r = new Vector2(getDisplayWidth(),alignY);
        localToScreenCoordinates(l);
        localToScreenCoordinates(r);
        float sw = r.cpy().sub(l).x;
        float sh = sw * getDisplayHeight() / getDisplayWidth();
        float sx = l.x;
        float sy = l.y - sh * alignY / getDisplayHeight();
        setRotation(originalRot);
        shapes.set(ShapeRenderer.ShapeType.Line);
        if (getStage() != null) shapes.setColor(getStage().getDebugColor());
        shapes.rect(sx, Gdx.graphics.getHeight() - sy, getOriginX(), getOriginY(), sw, sh,
                getScaleX(), getScaleY(), getRotation());
        shapes.setProjectionMatrix(proj.cpy());
    }
}
