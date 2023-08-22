package me.carina.rpg.client.battle;

import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.client.scenes.CanvasStage;
import me.carina.rpg.common.battle.BattleMap;

public class BattleScreen extends BaseScreen {
    BattleMapStage battleMapStage;
    CanvasStage actorStage;
    BattleMapGUIStage guiStage;

    @Override
    public void init() {
        battleMapStage = new BattleMapStage();
        addStage(battleMapStage);
        actorStage = new CanvasStage();
        addStage(actorStage);
        guiStage = new BattleMapGUIStage();
        addStage(guiStage);
    }

    @Override
    public CanvasStage getCanvas() {
        return actorStage;
    }

    @Override
    public boolean canChangeScreen() {
        return true;
    }

    public BattleMap getBattleMap(){
        return battleMapStage.getBattleMap();
    }

    public void setBattleMap(BattleMap battleMap){
        battleMapStage.setBattleMap(battleMap);
    }
}
