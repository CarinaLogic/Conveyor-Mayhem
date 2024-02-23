package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.UIAnimatedContainer;
import me.carina.rpg.common.unit.Unit;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {
    UIAnimatedContainer<UIBattleSkillPanel> skillPanel;
    @Override
    public void init(Table table) {
        table.add().height(64).expandX();
        table.row();
        skillPanel = new UIAnimatedContainer<>();
        table.add(skillPanel).top().right().expand().padRight(32);
        table.row();
        table.add(getScreen().map.factions.getFaction("").getUnits().newDisplay(UIBattleStatPanels.class)).left();
    }


    public Unit getSelectedUnit(){
        return getScreen().map.factions.getActiveUnit();
    }
}
