package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

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
    public void draw(Batch batch, float parentAlpha) {
        //TODO MOVE THIS TO act() so that it updates the parent hierarchy before it's drawn
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        resizeAll();
        super.draw(batch, parentAlpha);
    }

    public void resizeAll(){
        Unit unit = Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit();
        if (chachedUnit == unit) return;
        for (Cell<?> cell : getCells()) {
            cell.size(72, 48);
        }
        if (unit != null) {
            UIBattleStatPanel panel = unit.getDisplay(UIBattleStatPanel.class);
            Cell<?> cell = getCell(panel);
            if (cell != null) cell.size(96, 72);
        }
        chachedUnit = unit;
        invalidateHierarchy();
    }

    @Override
    public Units getFeature() {
        return units;
    }
}
