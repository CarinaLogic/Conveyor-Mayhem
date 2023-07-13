package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDisplay extends Display{
    Image image;

    public ImageDisplay(){
        this.image = new Image();
    }

    @Override
    public Feature getFeature() {
        return null;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
