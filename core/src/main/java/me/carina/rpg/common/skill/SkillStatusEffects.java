package me.carina.rpg.common.skill;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.file.AssetGroup;

public class SkillStatusEffects extends ArrayFeature<TargetedStatusEffect> {
    public SkillStatusEffects(){} //for json
    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
