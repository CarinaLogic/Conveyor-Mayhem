package me.carina.conveyor.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.conveyor.Game;
import me.carina.conveyor.client.ui.UIVerticalSelection;
import me.carina.conveyor.common.ArrayDisplayHandler;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.unit.Unit;

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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public UIBattleSkillPanel(Unit unit){
        this.unit = unit;
    }
    @Override
    public Unit getFeature() {
        return unit;
    }
}
