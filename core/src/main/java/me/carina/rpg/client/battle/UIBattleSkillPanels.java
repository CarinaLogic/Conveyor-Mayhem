package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.CursorHandler;
import me.carina.rpg.client.ui.UIAnimatedContainer;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

public class UIBattleSkillPanels extends UIAnimatedContainer<UIBattleSkillPanel> implements Display<Units>, CursorHandler {
    Units units;
    public UIBattleSkillPanels(Units units){
        this.units = units;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        placeUnitOnTop(Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void placeUnitOnTop(Unit unit){
        if (unit == null){
            for (Unit u : units) {
                u.removeDisplay(UIBattleSkillPanel.class);
            }
            clearChildren();
            return;
        }
        UIBattleSkillPanel panel = unit.getDisplay(UIBattleSkillPanel.class);
        if (getActor() == panel) return;
        addFromScreenRight(panel);
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
