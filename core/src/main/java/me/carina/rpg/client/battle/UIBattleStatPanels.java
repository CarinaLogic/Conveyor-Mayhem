package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.actions.Actions;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

import java.util.function.Supplier;

public class UIBattleStatPanels extends Table implements Display<Units> {
    Supplier<Units> unitsSupplier;
    Unit chachedUnit;
    ArrayDisplayHandler<Unit> handler = new ArrayDisplayHandler<>(
            this, feature -> add(Game.getClient().getDisplays().get(()->feature,UIBattleStatPanel.class)).size(72,48).bottom().left()
    ) {
        @Override
        public Iterable<Unit> getIterable() {
            return unitsSupplier.get();
        }
    };
    public UIBattleStatPanels(){
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
            UIBattleStatPanel panel = Game.getClient().getDisplays().get(()->unit, UIBattleStatPanel.class);
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
    public Supplier<Units> getFeatureSupplier() {
        return unitsSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Units> supplier) {
        this.unitsSupplier = supplier;
    }
}
