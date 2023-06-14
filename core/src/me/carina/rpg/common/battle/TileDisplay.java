package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.UnitDisplay;

public class TileDisplay extends Group implements Display {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
        if (tile.floor != null){
            FloorDisplay f = tile.floor.newDisplay();
            f.setSize(1,1);
            addActor(f);
        }
        if (tile.unit != null) {
            UnitDisplay u = tile.unit.newDisplay();
            addActor(u);
        }
        setSize(1,1);
        setPosition(tile.x,tile.y, Align.bottomLeft);
    }
}
