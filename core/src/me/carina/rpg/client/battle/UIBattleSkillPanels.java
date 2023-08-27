package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.CursorHandler;
import me.carina.rpg.client.ui.CursorPositionHolder;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

public class UIBattleSkillPanels extends Container<UIBattleSkillPanel> implements Display<Units>, CursorHandler {
    Units units;
    Unit currentSelection = null;
    public UIBattleSkillPanels(Units units){
        this.units = units;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        super.draw(batch, parentAlpha);
    }

    public boolean goLeft(){
        if (currentSelection == null) {
            currentSelection = units.get(units.size()-1);
            setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
            return true;
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        int index = units.indexIdentity(currentSelection);
        index--;
        if (index < 0) {
            currentSelection = null;
            setActor(null);
            return true;
        }
        currentSelection = units.get(index);
        setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
        return true;
    }
    public boolean goRight(){
        if (currentSelection == null) {
            currentSelection = units.get(0);
            setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
            return true;
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        int index = units.indexIdentity(currentSelection);
        index++;
        if (index >= units.size()) {
            currentSelection = null;
            setActor(null);
            return true;
        }
        currentSelection = units.get(index);
        setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
        return true;
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
