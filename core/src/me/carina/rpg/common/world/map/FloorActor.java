package me.carina.rpg.common.world.map;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class FloorActor extends Image {
    Floor floor;
    public FloorActor(Floor floor){
        this.floor = floor;
        setDrawable(floor.getGame().getAssets().get(floor.getPath(), Drawable.class));
    }
}
