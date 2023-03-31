package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;

public class Tile extends CompoundFeature {
    int x;
    int y;
    Element element;
    Floor floor;
    BattleActor battleActor;

    public Tile(AbstractGameInstance game) {
        super(game);
    }


    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setBattleActor(BattleActor battleActor) {
        this.battleActor = battleActor;
    }


    @Override
    public TileActor newActor() {
        return new TileActor(this);
    }

}
