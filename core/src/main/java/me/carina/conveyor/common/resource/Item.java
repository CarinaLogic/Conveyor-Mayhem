package me.carina.conveyor.common.resource;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;

public class Item extends Resource{
    @Override
    public Class<? extends Definition> getDefClass() {
        return Def.class;
    }
    public static class Def extends Feature.Def {

        @Override
        public void initFeature(Feature feature) {

        }
    }
}
