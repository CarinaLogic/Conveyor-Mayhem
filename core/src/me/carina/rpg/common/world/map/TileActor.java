package me.carina.rpg.common.world.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.common.world.unit.BattleActorActor;

public class TileActor extends Group {
    Tile tile;
    public TileActor(Tile tile){
        this.tile = tile;
        if (tile.floor != null){
            FloorActor floorActor = tile.floor.newActor();
            floorActor.setSize(1,1);
            addActor(floorActor);
        }
        if (tile.battleActor != null) {
            BattleActorActor battleActor = tile.battleActor.newActor();
            addActor(battleActor);
        }
        setSize(1,1);
        setPosition(tile.x,tile.y, Align.bottomLeft);
    }
}
