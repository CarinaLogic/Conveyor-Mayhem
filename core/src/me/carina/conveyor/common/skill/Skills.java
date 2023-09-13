package me.carina.conveyor.common.skill;

import me.carina.conveyor.common.ArrayFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.file.AssetGroup;

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
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
}
