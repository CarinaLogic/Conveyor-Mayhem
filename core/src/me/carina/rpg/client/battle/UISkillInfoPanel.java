package me.carina.rpg.client.battle;

import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

public class UISkillInfoPanel extends UITableView implements Display<Skill> {
    Skill skill;
    public UISkillInfoPanel(Skill skill){
        this.skill = skill;
        {
            
        }
    }

    @Override
    public Skill getFeature() {
        return skill;
    }
}
