package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kotcrab.vis.ui.widget.Draggable;
import me.carina.rpg.client.misc.Movable;

public class BattleMapActor extends Group{
    BattleMap map;
    public BattleMapActor(BattleMap map){
        this.map = map;
        for (Tile[] tiles : map.tiles) {
            for (Tile tile : tiles) {
                addActor(tile.newActor());
            }
        }
        setSize(map.getWidth(), getHeight());
        addListener(new Movable());
    }
}