package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.rpg.common.world.map.CanvasActor;

public abstract class FlatDisplay extends ImageDisplay{
    CanvasActor canvas;
    public FlatDisplay(CanvasActor canvas){
        this.canvas = canvas;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        setSize(getDisplayWidth(),getDisplayHeight());
        setPosition(getDisplayX(),getDisplayY(),getAlignment());

    }
}
