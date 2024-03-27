package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.actions.Actions;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

import java.util.Optional;
import java.util.function.Supplier;

public class UIBattleStatPanels extends Table implements Display<Units> {
    Supplier<Units> unitsSupplier;
    ArrayDisplayHandler<Unit,UIBattleStatPanel> handler = new ArrayDisplayHandler<>(
        this, UIBattleStatPanel.class, display -> add(display).bottom().left()){
        @Override
        public Units getIterable() {
            return unitsSupplier.get();
        }
    };
    public UIBattleStatPanels(){
        this.bottom().left();
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        resizeAll();
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void resizeAll(){
        Unit unit = Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit();
        Optional<UIBattleStatPanel> panel = Game.getClient().getDisplays().getOrNull(unit, UIBattleStatPanel.class);
        for (Actor child : getChildren()) {
            if (panel.equals(Optional.of(child))){
                child.addAction(Actions.uiSizeTo(96,72,0.2f));
            }
            else {
                child.addAction(Actions.uiSizeTo(72,48,0.2f));
            }
        }
    }

    @Override
    public Supplier<Units> getFeatureSupplier() {
        return unitsSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Units> supplier) {
        this.unitsSupplier = supplier;
    }
}
