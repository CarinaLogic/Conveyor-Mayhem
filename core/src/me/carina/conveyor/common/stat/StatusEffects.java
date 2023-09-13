package me.carina.conveyor.common.stat;

import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;

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
