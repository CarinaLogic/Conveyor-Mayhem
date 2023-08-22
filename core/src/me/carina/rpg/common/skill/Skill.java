package me.carina.rpg.common.skill;

import me.carina.rpg.common.Context;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Skill extends Feature {
    public Skill(){} //for json

    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.skills;
    }

    @Override
    public void tick() {

    }

    public static class Def extends Feature.Def{

        @Override
        public void initFeature(Feature feature) {
            if (feature instanceof Skill) {
                Skill skill = (Skill) feature;
                //TODO
            }

        }
    }
}
