package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;

public class Units extends ArrayFeature<Unit>{

    @Override
    public ArrayDisplay<Unit> newDisplay() {
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
