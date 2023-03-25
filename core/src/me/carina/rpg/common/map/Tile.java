package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;

public class Tile extends AbstractFeature{
    int x;
    int y;
    Element element;
    Floor floor;
    AbstractBattleActor battleActor;

    public Tile(AbstractGameInstance game) {
        super(game);
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setBattleActor(AbstractBattleActor battleActor) {
        this.battleActor = battleActor;
    }

    @Override
    public TileActor newActor() {
        return new TileActor(this);
    }
}
