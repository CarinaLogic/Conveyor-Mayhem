package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

public class TileActor extends Group {
    Tile tile;
    public TileActor(Tile tile){
        this.tile = tile;
        FloorActor floorActor = tile.floor.newActor();
        floorActor.setSize(1,1);
        addActor(floorActor);
        setSize(1,1);
        setPosition(tile.x,tile.y, Align.bottomLeft);
    }
}
