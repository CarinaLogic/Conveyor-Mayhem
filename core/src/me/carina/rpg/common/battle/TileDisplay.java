package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class TileDisplay extends Display {
    Tile tile;
    Image image;
    public TileDisplay(Tile tile){
        this.tile = tile;
        image = new Image();
    }

    @Override
    public Feature getFeature() {
        return tile;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
