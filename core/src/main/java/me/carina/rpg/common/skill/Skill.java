package me.carina.rpg.common.skill;

import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.command.Script;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.stat.Affinity;
import me.carina.rpg.common.stat.AffinityCounter;
import me.carina.rpg.common.util.Array;

public class Skill extends Feature {
    String name = "クラスターフォール";
    int growth;
    AffinityCounter power;
    int wt;
    int sp;
    int mp;
    AffinityCounter affinities;
    SkillStatusEffects statusEffects;
    Script animationScript;
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

    public int getMp() {
        return mp;
    }

    public int getSp() {
        return sp;
    }

    public int getWt() {
        return wt;
    }

    public int getGrowth() {
        return growth;
    }

    public AffinityCounter getPower() {
        return power;
    }

    public SkillStatusEffects getStatusEffects() {
        return statusEffects;
    }

    public AffinityCounter getAffinities() {
        return affinities;
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
