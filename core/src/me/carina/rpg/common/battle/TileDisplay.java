package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class TileDisplay extends Display {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
        addActor(new Image(Game.getInstance().getAssets().get(tile.getPath(), Drawable.class)));
    }

    @Override
    public Feature getFeature() {
        return tile;
    }
}
