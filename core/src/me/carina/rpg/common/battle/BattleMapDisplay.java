package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import me.carina.rpg.client.misc.Movable;
import me.carina.rpg.common.Display;

public class BattleMapDisplay extends Group implements Display {
    BattleMap map;
    public BattleMapDisplay(BattleMap map){
        this.map = map;
        for (Tile[] tiles : map.tiles) {
            for (Tile tile : tiles) {
                addActor(tile.newDisplay());
            }
        }
        setSize(map.getWidth(), map.getHeight());
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }
}
