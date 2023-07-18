package me.carina.rpg.common.skill;

import me.carina.rpg.common.Context;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;

public class Skill extends Feature {
    public Skill(){} //for json

    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }

    @Override
    public Display newDisplay() {
        return new SkillDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.skills;
    }

    @Override
    public void tickInner(Context context) {

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
