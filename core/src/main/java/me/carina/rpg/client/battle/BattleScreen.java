package me.carina.rpg.client.battle;

import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.client.scenes.CanvasStage;
import me.carina.rpg.common.battle.BattleMap;

public class BattleScreen extends BaseScreen {
    BattleMap map;
    BattleMapStage battleMapStage;
    CanvasStage actorStage;
    BattleMapGUIStage guiStage;
    public BattleScreen(BattleMap map){
        super();
        this.map = map;
    }

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

    public BattleMap getBattleMap() {
        return map;
    }

    public void setBattleMap(BattleMap map) {
        this.map = map;
    }

    @Override
    public boolean canChangeScreen() {
        return true;
    }
}
