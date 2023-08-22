package me.carina.rpg.common.world.map;

import com.badlogic.gdx.graphics.Color;
import me.carina.rpg.common.Context;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Faction extends Feature {
    Color mainColor;
    Color accentColorBright;
    Color accentColorDark;

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.teams;
    }

    @Override
    public void tick() {

    }

    @Override
    public Class<? extends Def> getDefClass() {
        return Def.class;
    }
    public static class Def extends Feature.Def{
        Color mainColor;
        Color accentColorBright;
        Color accentColorDark;
        @Override
        public void initFeature(Feature feature) {
            if (feature instanceof Faction) {
                Faction that = (Faction) feature;

            }
        }
    }
}
