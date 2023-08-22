package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.common.Array2DDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.util.Array2D;

public class TilesDisplay extends Group implements Display<Tiles> {
    Tiles tiles;
    Array2DDisplayHandler handler = new Array2DDisplayHandler(
            this, feature -> addActor(feature.newDisplay(TileDisplay.class))
    ) {
        @Override
        public Tiles getIterable() {
            return tiles;
        }
    };
    public TilesDisplay(Tiles tiles){
        this.tiles = tiles;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        handler.tick();
        super.draw(batch, parentAlpha);
    }

    @Override
    public Tiles getFeature() {
        return tiles;
    }
}
