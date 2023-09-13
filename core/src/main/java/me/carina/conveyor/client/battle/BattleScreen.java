package me.carina.conveyor.client.battle;

import me.carina.conveyor.client.scenes.BaseScreen;
import me.carina.conveyor.client.scenes.CanvasStage;
import me.carina.conveyor.common.battle.BattleMap;

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

    @Override
    public boolean canChangeScreen() {
        return true;
    }
}
