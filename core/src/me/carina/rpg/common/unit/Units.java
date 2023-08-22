package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.skill.Skill;

public class Units extends ArrayFeature<Unit>{
    Skill activeSkill;

    public Units(){
        add(new Unit());
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
