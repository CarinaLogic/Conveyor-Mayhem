package me.carina.rpg.client.battle;

import me.carina.rpg.Game;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;
import me.carina.rpg.common.stat.AffinityIconsDisplay;

import java.util.function.Supplier;

public class UISkillInfoPanel extends UITableView implements Display<Skill> {
    Supplier<Skill> skillSupplier;
    public UISkillInfoPanel(){
        add(new UILabel().supplyString(()->skillSupplier.get().getName()));
        row();
        add(Game.getClient().getDisplays().get(()->skillSupplier.get().getAffinities(),AffinityIconsDisplay.class));
        row();
        add(new UILabel().supplyString(() -> "MP"));
        add(new UILabel().supplyString(() -> Integer.toString(skillSupplier.get().getMp())));
        row();
        add(new UILabel().supplyString(() -> "SP"));
        add(new UILabel().supplyString(() -> Integer.toString(skillSupplier.get().getSp())));
        row();
        add(new UILabel().supplyString(() -> "WT"));
        add(new UILabel().supplyString(() -> Integer.toString(skillSupplier.get().getWt())));
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
