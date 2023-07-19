package me.carina.rpg.common.unit;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.common.util.FeatureArray;

public class UnitParts {
    FeatureArray<UnitPart> parts = new FeatureArray<>();
    public UnitPart getPart(BodyType type){
        return parts.firstMatch(p -> p.bodyType.equals(type));
    }
}
