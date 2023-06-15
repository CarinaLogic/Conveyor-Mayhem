package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.CompoundFeature;

public class BattleMap extends CompoundFeature {
    Tile[][] tiles;
    public BattleMap() {
        resize(10,10);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Tile tile = Game.getInstance().getAssets().get("core","plains", Tile.class);
                setTile(tile,x,y);
            }
        }
    }


    public void resize(int width, int height){
        Tile[][] newTiles = new Tile[width][height];
        for (int x = 0; x < getWidth(); x++) {
            System.arraycopy(tiles[x], 0, newTiles[x], 0, getHeight());
        }
        tiles = newTiles;
    }
    public void resize(int top, int bottom, int left, int right){
        int newWidth = tiles.length+top+bottom;
        int newHeight = tiles[0].length+left+right;
        Tile[][] newTiles = new Tile[newWidth][newHeight];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int newX = x + top;
                int newY = y + left;
                if (0 <= newX && newX < newWidth && 0 <= newY && newY < newHeight){
                    newTiles[newX][newY] = tiles[x][y];
                }
            }
        }
        tiles = newTiles;
    }
    public void setTile(Tile tile, int x, int y){
        tile.x = x;
        tile.y = y;
        if (0 <= tile.x && tile.x < getWidth() && 0 <= tile.y && tile.y < getHeight()){
            tiles[tile.x][tile.y] = tile;
        }
        else Game.getInstance().getLogger().error("Tile location ("+tile.x+", "+tile.y+") is invalid");
    }
    public int getWidth(){
        if (tiles == null) return 0;
        return tiles.length;
    }
    public int getHeight(){
        if (tiles == null) return 0;
        return tiles[0].length;
    }

    @Override
    public BattleMapDisplay newDisplay() {
        BattleMapDisplay d = new BattleMapDisplay(this);
        setDisplay(d);
        return d;
    }
}
