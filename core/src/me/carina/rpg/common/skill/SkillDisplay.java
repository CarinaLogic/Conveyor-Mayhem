package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.common.Display;

/**
 * Represents skill display
 */
public class SkillDisplay extends Image implements Display {
    //TODO
    Skill skill;
    public SkillDisplay(Skill skill){
        this.skill = skill;
    }
}
