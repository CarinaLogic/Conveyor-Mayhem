package me.carina.conveyor.client.battle;

import me.carina.conveyor.client.ui.UITableView;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.skill.Skill;

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
