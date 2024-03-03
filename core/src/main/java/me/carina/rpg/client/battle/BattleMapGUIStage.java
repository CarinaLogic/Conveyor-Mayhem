package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.UIAnimatedContainer;
import me.carina.rpg.common.faction.Faction;
import me.carina.rpg.common.unit.Unit;

import java.util.function.Supplier;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {
    UIAnimatedContainer<UIBattleSkillPanel> skillPanel;
    static Supplier<Unit> invSelectedUnit = () -> Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit();
    @Override
    public void init(Table table) {
        table.add().height(64).expandX();
        table.row();
        skillPanel = new UIAnimatedContainer<UIBattleSkillPanel>()
            .supplyDirection(() -> UIAnimatedContainer.Direction.right)
            .supplyActor(() -> Game.getClient().getDisplays().get(invSelectedUnit,UIBattleSkillPanel.class));
        table.add(skillPanel).top().right().expand().padRight(32);
        table.row();
        table.add(Game.getClient().getDisplays().get(()->getScreen().map.factions.getFaction("").getUnits() ,UIBattleStatPanels.class)).left();
    }


    public Unit getSelectedUnit(){
        return getScreen().map.factions.getActiveUnit();
    }
}
