package me.carina.rpg.common.item;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

public class ItemDisplay extends Display {
    Item item;
    public ItemDisplay(Item item){
        this.item = item;
    }

    @Override
    public Feature getFeature() {
        return item;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
