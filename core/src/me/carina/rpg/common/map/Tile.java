package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;

public class Tile extends AbstractFeature{
    int x;
    int y;
    Element element;
    Floor floor;

    public Tile(AbstractGameInstance game, Floor floor) {
        super(game);
        this.floor = floor;
    }

    @Override
    public TileActor newActor() {
        return new TileActor(this);
    }
}
