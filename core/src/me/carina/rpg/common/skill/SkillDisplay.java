package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;

/**
 * Represents skill display
 */
public class SkillDisplay extends Display {
    //TODO
    Skill skill;
    public SkillDisplay(Skill skill){
        this.skill = skill;
    }

    @Override
    public Feature getFeature() {
        return skill;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
