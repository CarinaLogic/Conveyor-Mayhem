package me.carina.conveyor.client.battle;

import me.carina.conveyor.client.ui.Selectable;
import me.carina.conveyor.client.ui.UILabelButton;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.skill.Skill;

@Selectable
public class UIBattleSkillEntry extends UILabelButton implements Display<Skill> {
    Skill skill;
    public UIBattleSkillEntry(Skill skill) {
        super();
        this.skill = skill;
        text(skill.getName());
    }

    @Override
    public Skill getFeature() {
        return skill;
    }
}
