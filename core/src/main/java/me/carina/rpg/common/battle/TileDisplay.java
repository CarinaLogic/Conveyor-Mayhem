package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.ImageDisplay;

import java.util.function.Supplier;

public class TileDisplay extends ImageDisplay implements Display<Tile> {
    Supplier<Tile> tileSupplier;
    public TileDisplay(){
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }


    @Override
    public float getDisplayX() {
        return Game.getInstance().getContext().get(Tiles.class).getIdenticalX(tileSupplier.get());
    }

    @Override
    public float getDisplayY() {
        return Game.getInstance().getContext().get(Tiles.class).getIdenticalY(tileSupplier.get());
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
    public Drawable getDrawable() {
        return Game.getInstance().getAssets().get(tileSupplier.get().getPath(), Drawable.class);
    }

    @Override
    public Supplier<Tile> getFeatureSupplier() {
        return tileSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Tile> supplier) {
        this.tileSupplier = supplier;
    }
}
