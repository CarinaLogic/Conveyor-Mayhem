package me.carina.rpg.common.world.map;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.world.CompoundFeature;
import me.carina.rpg.common.world.unit.BattleActor;

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
