package me.carina.rpg.client.battle;

import me.carina.rpg.client.ui.Selectable;
import me.carina.rpg.client.ui.UILabelButton;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

@Selectable
public class UIBattleSkillEntry extends UILabelButton implements Display<Skill> {
    Skill skill;
    public UIBattleSkillEntry(Skill skill) {
        super();
        this.skill = skill;
        supplyString(skill::getName);
    }

    @Override
    public Skill getFeature() {
        return skill;
    }
}
