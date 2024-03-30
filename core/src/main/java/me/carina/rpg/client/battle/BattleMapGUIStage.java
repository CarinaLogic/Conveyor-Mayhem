package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.UIAnimatedContainer;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.faction.Faction;
import me.carina.rpg.common.skill.Skill;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.Units;

import java.util.function.Supplier;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {
    UIAnimatedContainer<UIBattleSkillPanel> skillPanel;
    UIAnimatedContainer<UIBattleSkillInfoPanel> skillInfoPanel;
    static Supplier<Unit> invSelectedUnit = () -> Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit();
    static Supplier<Skill> invSelectedSkill = () -> Game.getClient().getContext().get(BattleMapGUIStage.class).skillPanel.unpack().getSelectedSkill();
    static Supplier<Units> invFactionUnits = ()-> Game.getClient().getContext().get(BattleMap.class).factions.getFaction("").getUnits();
    @Override
    public void init(Table table) {
        table.add().height(64).expandX();
        table.row();
        skillPanel = new UIAnimatedContainer<UIBattleSkillPanel>()
            .supplyDirection(() -> UIAnimatedContainer.Direction.right)
            .supplyActor(() -> Game.getClient().getDisplays().get(
                invSelectedUnit,UIBattleSkillPanel.class));
        skillInfoPanel = new UIAnimatedContainer<UIBattleSkillInfoPanel>()
            .supplyDirection(() -> UIAnimatedContainer.Direction.right)
            .supplyActor(() -> Game.getClient().getDisplays().get(
                invSelectedSkill,UIBattleSkillInfoPanel.class));
        table.add(skillPanel).top().right().expand();
        //table.add(skillInfoPanel).top().right().padRight(8);
        table.row();
        table.add(Game.getClient().getDisplays().get(
            invFactionUnits ,UIBattleStatPanels.class)).left();
    }


    public Unit getSelectedUnit(){
        return getScreen().map.factions.getActiveUnit();
    }
}
