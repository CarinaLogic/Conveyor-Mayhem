package me.carina.conveyor.common.unit;

import me.carina.conveyor.common.*;
import me.carina.conveyor.common.file.AssetGroup;

public class Units extends ArrayFeature<Unit>{
   public Units(){
        Unit unit1 = new Unit();
        unit1.x = 3;
        unit1.y = 1;
        add(unit1);
        Unit unit2 = new Unit();
        unit2.x = 0;
        unit2.y = 9;
        add(unit2);
        Unit unit3 = new Unit();
        unit3.x = 4;
        unit3.y = 5;
        add(unit3);
        Unit unit4 = new Unit();
        unit4.x = 7;
        unit4.y = 9;
        add(unit4);
        Unit unit5 = new Unit();
        unit5.x = 6;
        unit5.y = 3;
        add(unit5);
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
