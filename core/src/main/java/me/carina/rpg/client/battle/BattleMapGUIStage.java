package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.UIAnimatedContainer;
import me.carina.rpg.common.unit.Unit;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {
    int selectedIndex = -1;
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

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public Unit getSelectedUnit(){
        if (selectedIndex == -1) return null;
        return getScreen().map.factions.getFaction("").getUnits().get(selectedIndex);
    }

    @Override
    public boolean goLeft() {
        if (selectedIndex == -1){
            selectedIndex = getScreen().map.factions.getFaction("").getUnits().size()-1;
            return true;
        }
        selectedIndex--;
        if (selectedIndex < 0) selectedIndex = -1;
        return true;
    }

    @Override
    public boolean goRight() {
        if (selectedIndex == -1){
            selectedIndex = 0;
            return true;
        }
        selectedIndex++;
        if (selectedIndex >= getScreen().map.factions.getFaction("").getUnits().size()) selectedIndex = -1;
        return true;
    }
}
