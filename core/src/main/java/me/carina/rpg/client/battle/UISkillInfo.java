package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

public class UISkillInfo extends UITableView implements Display<Skill> {
    Skill skill;
    public UISkillInfo(Skill skill){
        this.skill = skill;
        add(new UILabel().supply(skill::getName));
        row();

    }
    @Override
    public Skill getFeature() {
        return skill;
    }
}
