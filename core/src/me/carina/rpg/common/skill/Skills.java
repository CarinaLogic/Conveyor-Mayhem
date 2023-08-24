package me.carina.rpg.common.skill;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.util.Array;

public class Skills extends ArrayFeature<Skill> {
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
