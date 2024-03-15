package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.UIVerticalSelection;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.skill.Skill;
import me.carina.rpg.common.skill.Skills;
import me.carina.rpg.common.unit.Unit;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class UIBattleSkillPanel extends UIVerticalSelection implements Display<Unit> {
    Supplier<Unit> unitSupplier;
    ArrayDisplayHandler<Skill,UIBattleSkillEntry> handler = new ArrayDisplayHandler<>(
            this, UIBattleSkillEntry.class, this::add)
    {
        @Override
        public Skills getIterable() {
            return unitSupplier.get().getSkills();
        }
    };


    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }

    public UIBattleSkillPanel(){
        super();
    }

    @Override
    public Supplier<Unit> getFeatureSupplier() {
        return unitSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Unit> supplier) {
        this.unitSupplier = supplier;
    }
}
