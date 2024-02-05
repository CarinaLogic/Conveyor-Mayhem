package me.carina.rpg.common.skill;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.file.AssetGroup;

public class Skills extends ArrayFeature<Skill> {
    public Skills(){
        add(new Skill());
        add(new Skill());
        add(new Skill());
        add(new Skill());
    }
    public boolean add(Skill skill){
        if (this.getArray().size >= 10) return false;
        super.add(skill);
        return true;
    }


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
