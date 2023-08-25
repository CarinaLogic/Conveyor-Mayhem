package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

public class UIBattleSkillPanels extends Stack implements Display<Units> {
    Units units;
    Unit currentSelection = null;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> add(((Unit) feature).getSkills().newDisplay(UIBattleSkillPanel.class))
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return units;
        }
    };
    public UIBattleSkillPanels(Units units){
        this.units = units;
    }

    @Override
    public void add(Actor actor) {
        if (currentSelection != null) actor.setVisible(false);
        else {
            getStage().setKeyboardFocus(((UIBattleSkillPanel) actor).getChild(0));
        }
        super.add(actor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        if (currentSelection != null && !units.contains(currentSelection)) currentSelection = units.get(0);
        super.draw(batch, parentAlpha);
    }

    public void goLeft(){
        if (currentSelection == null) {
            currentSelection = units.get(units.size()-1);
            return;
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        currentSelection.getDisplay(UIBattleSkillPanel.class).setVisible(false);
        int index = units.indexIdentity(currentSelection);
        index--;
        if (index < 0) currentSelection = null;
        currentSelection = units.get(index);
        currentSelection.getDisplay(UIBattleSkillPanel.class).setVisible(true);
    }
    public void goRight(){
        if (currentSelection == null) {
            currentSelection = units.get(0);
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        currentSelection.getDisplay(UIBattleSkillPanel.class).setVisible(false);
        int index = units.indexIdentity(currentSelection);
        index++;
        if (index >= units.size()) currentSelection = null;
        currentSelection = units.get(index);
        currentSelection.getDisplay(UIBattleSkillPanel.class).setVisible(true);
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
