package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.common.Display;

/**
 * Represents skill display
 */
public class SkillDisplay extends Stack implements Display<Skill> {
    //TODO
    Skill skill;
    public SkillDisplay(Skill skill){
        this.skill = skill;
    }

    @Override
    public Skill getFeature() {
        return skill;
    }
}
