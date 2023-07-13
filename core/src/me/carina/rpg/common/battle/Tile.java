package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.DrawableFeature;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Tile extends DrawableFeature {
    public Tile(){} //for json
    @Override
    public TileDisplay newDisplay() {
        TileDisplay d = new TileDisplay(this);
        setDisplay(d);
        return d;
    }

    @Override
    public float getDisplayX() {
        return ((BattleScreen) ((Client) Game.getInstance()).getScreen()).getBattleMap().tiles.getTiles().getIdenticalX(this);
    }

    @Override
    public float getDisplayY() {
        return ((BattleScreen) ((Client) Game.getInstance()).getScreen()).getBattleMap().tiles.getTiles().getIdenticalY(this);
    }

    @Override
    public float getDisplayWidth() {
        return 1;
    }

    @Override
    public float getDisplayHeight() {
        return 1;
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.tiles;
    }

    @Override
    public Drawable getDrawable() {
        return Game.getInstance().getAssets().get(getPath(), Drawable.class);
    }


    public static class Def extends Feature.Def{
        @Override
        public void initFeature(Feature feature) {
            //NOOP
        }
    }
}
