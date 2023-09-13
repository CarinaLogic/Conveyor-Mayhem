package me.carina.conveyor.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.conveyor.Game;
import me.carina.conveyor.client.actions.Actions;
import me.carina.conveyor.common.ArrayDisplayHandler;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.unit.Unit;
import me.carina.conveyor.common.unit.Units;

public class UIBattleStatPanels extends Table implements Display<Units> {
    Units units;
    Unit chachedUnit;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> add(feature.newDisplay(UIBattleStatPanel.class)).size(72,48).bottom().left()
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return units;
        }
    };
    public UIBattleStatPanels(Units units){
        this.units = units;
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
        if (chachedUnit == unit) return;
        Cell<?> currentTargetCell = null;
        if (unit != null) {
            UIBattleStatPanel panel = unit.getDisplay(UIBattleStatPanel.class);
            Cell<?> cell = getCell(panel);
            if (cell != null) currentTargetCell = cell;
        }
        for (Cell<?> cell : getCells()) {
            if (cell == currentTargetCell) cell.getActor().addAction(Actions.uiSizeTo(96,72,0.2f));
            else cell.getActor().addAction(Actions.uiSizeTo(72,48,0.2f));
        }
        chachedUnit = unit;
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
