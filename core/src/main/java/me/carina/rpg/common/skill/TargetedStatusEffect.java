package me.carina.rpg.common.skill;

import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.stat.StatusEffect;

public class TargetedStatusEffect extends Feature {
    StatusEffect effect;
    StatTargetTypes target;
    public TargetedStatusEffect(){} //for json

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }
}
