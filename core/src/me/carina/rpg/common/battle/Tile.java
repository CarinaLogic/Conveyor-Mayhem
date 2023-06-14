package me.carina.rpg.common.battle;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.CompoundFeature;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.world.map.Element;

public class Tile extends CompoundFeature {
    int x;
    int y;
    Floor floor;
    Unit unit;

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


    @Override
    public TileDisplay newDisplay() {
        return new TileDisplay(this);
    }
}
