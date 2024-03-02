package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.common.Display;

import java.util.function.Supplier;

/**
 * Represents skill display
 */
public class SkillDisplay extends Stack implements Display<Skill> {
    //TODO
    Supplier<Skill> skillSupplier;
    public SkillDisplay(){
    }

    @Override
    public Supplier<Skill> getFeatureSupplier() {
        return skillSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Skill> supplier) {
        this.skillSupplier = supplier;
    }

}
