package me.carina.rpg.common.unit;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.FeatureArray;

public class UnitParts extends Feature{
    @AutoDisplay
    public FeatureArray<UnitPart> parts = new FeatureArray<>();
    public UnitPart getPart(BodyType type){
        return parts.firstMatch(p -> p.bodyType.equals(type));
    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public Display newDisplay() {
        return new UnitPartsDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {

    }
}
