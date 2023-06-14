package me.carina.rpg.common.item;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.common.Display;

public class ItemDisplay extends Image implements Display {
    Item item;
    public ItemDisplay(Item item){
        this.item = item;
    }
}
