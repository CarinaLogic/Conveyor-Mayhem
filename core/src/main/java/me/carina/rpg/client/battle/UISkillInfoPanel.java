package me.carina.rpg.client.battle;

import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;
import me.carina.rpg.common.stat.AffinityIconsDisplay;

public class UISkillInfoPanel extends UITableView implements Display<Skill> {
    Skill skill;
    public UISkillInfoPanel(Skill skill){
        this.skill = skill;
        add(new UILabel().supply(skill::getName));
        row();
        add(skill.getAffinities().newDisplay(AffinityIconsDisplay.class));
        row();
        add(new UILabel().supply(() -> Integer.toString(skill.getMp())));
    }
    @Override
    public Skill getFeature() {
        return skill;
    }
}
