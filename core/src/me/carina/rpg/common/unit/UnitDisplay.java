package me.carina.rpg.common.unit;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.common.Display;

public class UnitDisplay extends Group implements Display {
    Unit unit;
    public UnitDisplay(Unit unit){
        this.unit = unit;
    }
}
