package me.carina.rpg.common.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Array;

public class StackedDrawable extends BaseDrawable implements TransformDrawable {
    Array<TransformDrawable> drawables = new Array<>();
    public StackedDrawable(){}
    public StackedDrawable(TransformDrawable... drawables){
        this.drawables.addAll(drawables);
    }
    public StackedDrawable(TextureRegion... textureRegions){
        for (TextureRegion textureRegion : textureRegions) {
            this.drawables.add(new TextureRegionDrawable(textureRegion));
        }
    }
    public void addDrawable(TransformDrawable drawable){
        drawables.add(drawable);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        for (TransformDrawable drawable : drawables) {
            drawable.draw(batch, x, y, width, height);
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation) {
        for (TransformDrawable drawable : drawables) {
            drawable.draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
        }
    }

    @Override
    public void setBottomHeight(float bottomHeight) {
        drawables.forEach(d -> d.setBottomHeight(bottomHeight));
    }

    @Override
    public void setLeftWidth(float leftWidth) {
        drawables.forEach(d -> d.setLeftWidth(leftWidth));
    }

    @Override
    public void setMinHeight(float minHeight) {
        drawables.forEach(d -> d.setMinHeight(minHeight));
    }

    @Override
    public void setMinSize(float minWidth, float minHeight) {
        drawables.forEach(d -> {
            d.setMinWidth(minWidth);
            d.setMinHeight(minHeight);
        });
    }

    @Override
    public void setMinWidth(float minWidth) {
        drawables.forEach(d -> d.setMinWidth(minWidth));
    }

    @Override
    public void setPadding(float topHeight, float leftWidth, float bottomHeight, float rightWidth) {
        drawables.forEach(d -> {
            d.setTopHeight(topHeight);
            d.setBottomHeight(bottomHeight);
            d.setLeftWidth(leftWidth);
            d.setRightWidth(rightWidth);
        });
    }

    @Override
    public void setRightWidth(float rightWidth) {
        drawables.forEach(d -> d.setRightWidth(rightWidth));
    }

    @Override
    public void setTopHeight(float topHeight) {
        drawables.forEach(d -> d.setTopHeight(topHeight));
    }
}
