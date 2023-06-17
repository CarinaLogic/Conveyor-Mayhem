package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import me.carina.rpg.client.misc.Movable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.util.Array2D;

public class BattleMapDisplay extends Group implements Display {
    BattleMap map;
    public BattleMapDisplay(BattleMap map){
        this.map = map;
        for (Array2D.Array2DEntry<Tile> tiles : map.tiles) {
            addActor(tiles.value.newDisplay());
        }
        setSize(map.getWidth(), map.getHeight());
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
