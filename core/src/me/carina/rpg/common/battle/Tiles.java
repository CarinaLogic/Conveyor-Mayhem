package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array2D;

public class Tiles extends Array2D<Tile> {
    public Tiles(){
        //debug purposes
        super(10,10);
        fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
    }
    @Override
    public void set(int x, int y, Tile obj) {
        super.set(x, y, obj);
        obj.setPos(x, y);
    }
}
