package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.common.Display;

public class FloorDisplay extends Image implements Display {
    Floor floor;
    public FloorDisplay(Floor floor){
        this.floor = floor;
        setDrawable(floor.getGame().getAssets().get(floor.getPath(), Drawable.class));
    }
}
