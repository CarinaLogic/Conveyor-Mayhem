package me.carina.rpg.common.unit.stat;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.file.AssetGroup;

public class StatusEffects extends ArrayFeature<StatusEffect> {
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    public void turn(){
        for (StatusEffect statusEffect : this) {
            statusEffect.duration -= 1;
        }
    }
}
