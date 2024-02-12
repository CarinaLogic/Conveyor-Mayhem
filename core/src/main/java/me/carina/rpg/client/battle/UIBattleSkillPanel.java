package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.UIVerticalSelection;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;

public class UIBattleSkillPanel extends UIVerticalSelection implements Display<Unit> {
    Unit unit;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> add(feature.newDisplay(UIBattleSkillEntry.class))
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return unit.getSkills();
        }
    };

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }

    public UIBattleSkillPanel(Unit unit){
        this.unit = unit;
    }
    @Override
    public Unit getFeature() {
        return unit;
    }
}
