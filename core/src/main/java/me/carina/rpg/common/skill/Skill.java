package me.carina.rpg.common.skill;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Skill extends Feature {
    String name = "bruh moment";
    public Skill(){} //for json

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

    public static class Def extends Definition<Skill>{

        @Override
        public Class<Skill> getDefinedClass() {
            return Skill.class;
        }

        @Override
        public void init(Skill definedObject) {

        }
    }
}
