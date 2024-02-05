package me.carina.rpg.common.resource;

import me.carina.rpg.common.Definition;

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
