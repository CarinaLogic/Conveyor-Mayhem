package me.carina.rpg.common.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;

public class Faction extends Feature{
    Color mainColor;
    Color accentColorBright;
    Color accentColorDark;

    public Faction(AbstractGameInstance game, Def def) {
        super(game, def);
    }

    public Faction(AbstractGameInstance game, Identifier id) {
        super(game, id);
    }


    @Override
    public Actor newActor() {
        return new FactionActor(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.factions;
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
        public void init(Feature feature) {
            if (feature instanceof Faction) {
                Faction that = (Faction) feature;

            }
        }
    }
}
