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
        add(new UILabel().supplyString(skill::getName));
        row();
        add(skill.getAffinities().newDisplay(AffinityIconsDisplay.class));
        row();
        add(new UILabel().supplyString(() -> "MP"));
        add(new UILabel().supplyString(() -> Integer.toString(skill.getMp())));
        row();
        add(new UILabel().supplyString(() -> "SP"));
        add(new UILabel().supplyString(() -> Integer.toString(skill.getSp())));
        row();
        add(new UILabel().supplyString(() -> "WT"));
        add(new UILabel().supplyString(() -> Integer.toString(skill.getWt())));
    }
    @Override
    public Skill getFeature() {
        return skill;
    }
}
