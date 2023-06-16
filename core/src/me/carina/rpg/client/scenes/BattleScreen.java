package me.carina.rpg.client.scenes;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.battle.BattleMap;

public class BattleScreen extends BaseScreen{
    BattleMapStage battleMapStage;
    BattleMapActorStage actorStage;
    BattleMapGUIStage guiStage;

    @Override
    public void show() {
        battleMapStage = new BattleMapStage();
        addStage(battleMapStage);
        actorStage = new BattleMapActorStage();
        addStage(actorStage);
        guiStage = new BattleMapGUIStage();
        addStage(guiStage);
        battleMapStage.setCanvas(actorStage.canvas);
    }

    @Override
    public boolean canChangeScreen() {
        return true;
    }

    public void setBattleMap(BattleMap battleMap){
        battleMapStage.setBattleMap(battleMap);
    }
}
