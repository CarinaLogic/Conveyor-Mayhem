package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.CursorHandler;
import me.carina.rpg.client.ui.CursorPositionHolder;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

public class UIBattleSkillPanels extends Stack implements Display<Units>, CursorHandler {
    Units units;
    public UIBattleSkillPanels(Units units){
        this.units = units;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        placeUnitOnTop(Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit());
        super.draw(batch, parentAlpha);
    }

    public void placeUnitOnTop(Unit unit){
        if (unit == null){
            clearChildren();
            return;
        }
        UIBattleSkillPanel panel = unit.getDisplay(UIBattleSkillPanel.class);
        if (getChildren().notEmpty() && getChildren().peek() == panel) return;
        removeActor(panel);
        addActor(panel);
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
