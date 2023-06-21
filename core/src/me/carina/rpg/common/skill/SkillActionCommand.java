package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.function.Function;

public enum SkillActionCommand {
    ;
    final Function<String[],Action> func;
    SkillActionCommand(Function<String[],Action> func){
        this.func = func;
    }
    public Action eval(String... args){
        return func.apply(args);
    }
}
