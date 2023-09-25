package me.carina.conveyor.common.unit;

import me.carina.conveyor.common.*;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.file.Identifier;

public class UnitParts extends ArrayFeature<UnitPart>{
    public UnitParts(){
        add(new UnitPart(BodyType.base, new Identifier("core","basic"), BodyProperty.wait, 0));
        add(new UnitPart(BodyType.eyes, new Identifier("core","basic"), BodyProperty.wait, 0));
        add(new UnitPart(BodyType.body, new Identifier("core","basic"), BodyProperty.wait, 2));
        add(new UnitPart(BodyType.head, new Identifier("core","basic"), BodyProperty.wait, 0));
        add(new UnitPart(BodyType.legs, new Identifier("core","basic"), BodyProperty.wait, 0));
        add(new UnitPart(BodyType.hair, new Identifier("core","basic"), BodyProperty.wait, 0));
    }

    public UnitPart getPart(BodyType type){
        return getArray().firstMatch(p -> p.bodyType.equals(type));
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

}
