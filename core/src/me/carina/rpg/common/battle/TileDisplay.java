package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;

public class TileDisplay extends Image implements Display {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
        setDrawable(tile.getGame().getAssets().get(tile.getPath(), Drawable.class));
        setSize(1,1);
        setPosition(tile.x,tile.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
