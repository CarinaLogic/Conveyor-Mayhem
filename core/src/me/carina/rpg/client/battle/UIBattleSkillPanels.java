package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

public class UIBattleSkillPanels extends Container<UIBattleSkillPanel> implements Display<Units> {
    Units units;
    Unit currentSelection = null;
    public UIBattleSkillPanels(Units units){
        this.units = units;
        addCaptureListener(new CursorListener(){
            @Override
            public boolean left(InputEvent event) {
                goLeft();
                return true;
            }

            @Override
            public boolean right(InputEvent event) {
                goRight();
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        super.draw(batch, parentAlpha);
    }

    public void goLeft(){
        if (currentSelection == null) {
            currentSelection = units.get(units.size()-1);
            return;
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        int index = units.indexIdentity(currentSelection);
        index--;
        if (index < 0) {
            currentSelection = null;
            setActor(null);
        }
        currentSelection = units.get(index);
        setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
    }
    public void goRight(){
        if (currentSelection == null) {
            currentSelection = units.get(0);
        }
        if (!units.contains(currentSelection)) currentSelection = units.get(0);
        int index = units.indexIdentity(currentSelection);
        index++;
        if (index >= units.size()) {
            currentSelection = null;
            setActor(null);
        }
        currentSelection = units.get(index);
        setActor(currentSelection.newDisplay(UIBattleSkillPanel.class));
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
