package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.Array2DDisplayHandler;
import me.carina.rpg.common.Display;

import java.util.function.Supplier;

public class TilesDisplay extends Group implements Display<Tiles> {
    Supplier<Tiles> tilesSupplier;
    Array2DDisplayHandler<Tile> handler = new Array2DDisplayHandler<>(
            this, feature -> addActor(Game.getClient().getDisplays().get(()->feature,TileDisplay.class)))
    {
        @Override
        public Tiles getIterable() {
            return tilesSupplier.get();
        }
    };
    public TilesDisplay(){
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }

    @Override
    public Supplier<Tiles> getFeatureSupplier() {
        return tilesSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Tiles> supplier) {
        this.tilesSupplier = supplier;
    }
}
