package me.carina.conveyor.common.resource;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;

public class Item extends Resource{
    public Item(){
        type = ResourceType.item;
    }

    public static class Def extends Definition<Item> {

        @Override
        public Class<Item> getDefinedClass() {
            return Item.class;
        }

        @Override
        public void init(Item definedObject) {

        }
    }
}
