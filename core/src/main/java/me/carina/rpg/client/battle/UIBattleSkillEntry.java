package me.carina.rpg.client.battle;

import me.carina.rpg.client.ui.Selectable;
import me.carina.rpg.client.ui.UILabelButton;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

import java.util.function.Supplier;

@Selectable
public class UIBattleSkillEntry extends UILabelButton implements Display<Skill> {
    Supplier<Skill> skillSupplier;
    public UIBattleSkillEntry() {
        super();
        supplyString(()->skillSupplier.get().getName());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
