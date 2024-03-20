package me.carina.rpg.client.battle;

import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

import java.util.function.Supplier;

public class UIBattleSkillInfoPanel extends UITableView implements Display<Skill> {
    Supplier<Skill> skillSupplier = () -> null;
    public UIBattleSkillInfoPanel(){

    }
    @Override
    public void init() {
        add(new UILabel().supplyString(()->skillSupplier.get().getName()));
        row();
        add(new UILabel().supplyString(()->"WT"));
        add(new UILabel().supplyString(()-> String.valueOf(skillSupplier.get().getWt())));
        add(new UILabel().supplyString(()->"Growth"));
        add(new UILabel().supplyString(()-> String.valueOf(skillSupplier.get().getGrowth())));
        row();
        add(new UILabel().supplyString(()->"SP"));
        add(new UILabel().supplyString(()-> String.valueOf(skillSupplier.get().getSp())));
        add(new UILabel().supplyString(()->"MP"));
        add(new UILabel().supplyString(()-> String.valueOf(skillSupplier.get().getMp())));
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
