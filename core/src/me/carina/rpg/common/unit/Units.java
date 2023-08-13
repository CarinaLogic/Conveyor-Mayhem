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
    protected ArrayDisplay<Unit> newDisplay() {
        return new UnitsDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {

    }

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
