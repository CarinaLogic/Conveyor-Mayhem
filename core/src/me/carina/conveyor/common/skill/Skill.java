package me.carina.conveyor.common.skill;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

public class Skill extends Feature {
    String name = "bruh moment";
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

    public String getName() {
        return name;
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
