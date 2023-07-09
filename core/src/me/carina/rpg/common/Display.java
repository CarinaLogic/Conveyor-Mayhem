package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public abstract class Display extends Group {
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setSize(getFeature().getDisplayWidth(),getFeature().getDisplayHeight());
        setPosition(getFeature().getDisplayX(),getFeature().getDisplayY());
        super.draw(batch, parentAlpha);
    }

    @Override
    public void setSize(float width, float height) {
        float w = super.getWidth();
        float h = super.getHeight();
        super.setSize(width,height);
        for (Actor child : getChildren()) {
            float cw = child.getWidth();
            float ch = child.getHeight();
            child.setSize(cw*width/w, ch*height/h);
        }
    }

    public abstract Feature getFeature();

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
    }
}
