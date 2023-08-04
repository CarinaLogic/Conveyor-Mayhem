package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.ImageDisplay;

public class TileDisplay extends ImageDisplay {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
    }

    @Override
    public void tick() {

    }

    @Override
    public float getDisplayX() {
        return getContext().get(BattleMap.class).tiles.getX(tile);
    }

    @Override
    public float getDisplayY() {
        return getContext().get(BattleMap.class).tiles.getY(tile);
    }

    @Override
    public float getDisplayWidth() {
        return 1;
    }

    @Override
    public float getDisplayHeight() {
        return 1;
    }

    @Override
    public Tile getFeature() {
        return tile;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }

    @Override
    public Drawable getDrawable() {
        return Game.getInstance().getAssets().get(tile.getPath(), Drawable.class);
    }
}
