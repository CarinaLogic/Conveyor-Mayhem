package me.carina.conveyor.common.battle;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.ImageDisplay;

public class TileDisplay extends ImageDisplay implements Display<Tile> {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }


    @Override
    public float getDisplayX() {
        return Game.getInstance().getContext().get(Tiles.class).getIdenticalX(tile);
    }

    @Override
    public float getDisplayY() {
        return Game.getInstance().getContext().get(Tiles.class).getIdenticalY(tile);
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
    public Drawable getDrawable() {
        return Game.getInstance().getAssets().get(tile.getPath(), Drawable.class);
    }
}
