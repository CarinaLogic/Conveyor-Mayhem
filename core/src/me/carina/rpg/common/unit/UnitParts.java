package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;

public class UnitParts extends ArrayFeature<UnitPart>{

    @Override
    public ArrayDisplay<UnitPart> newDisplay() {
        return new UnitPartsDisplay(this);
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
