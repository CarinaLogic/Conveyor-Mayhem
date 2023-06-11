package me.carina.rpg.common.world.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import me.carina.rpg.client.misc.Movable;

public class WorldMapActor extends Group{
    WorldMap map;
    public WorldMapActor(WorldMap map){
        this.map = map;
        for (Tile[] tiles : map.tiles) {
            for (Tile tile : tiles) {
                addActor(tile.newActor());
            }
        }
        setSize(map.getWidth(), map.getHeight());
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }
}
