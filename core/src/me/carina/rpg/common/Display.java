package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Display extends Group {
    transient Context context;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (getParent() != null && getParent() instanceof Display){
            Display p = ((Display) getParent());
            if (p.context != null) {
                this.context = p.context.clone();
                this.context.add(this.getFeature());
            }
            else {
                this.context = new Context();
                this.context.add(this.getFeature());
            }
        }
        else {
            this.context = new Context();
            this.context.add(this.getFeature());
        }
        setSize(getDisplayWidth(), getDisplayHeight());
        setPosition(getDisplayX(), getDisplayY());
        super.draw(batch, parentAlpha);
    }
    @Override
    public void setSize(float width, float height) {
        float w = super.getWidth();
        float h = super.getHeight();
        super.setSize(width,height);
        for (Actor child : getChildren()) {
            if (fillChildren()){
                child.setSize(width, height);
            }
            else{
                float cw = child.getWidth();
                float ch = child.getHeight();
                child.setSize(cw*width/w, ch*height/h);
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public abstract float getDisplayX();
    public abstract float getDisplayY();

    public abstract float getDisplayWidth();
    public abstract float getDisplayHeight();
    public abstract Feature getFeature();

    public abstract boolean fillChildren();

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
    }
}
