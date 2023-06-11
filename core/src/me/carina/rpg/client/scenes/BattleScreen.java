package me.carina.rpg.client.scenes;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.world.map.WorldMap;

public class BattleScreen extends BaseScreen{
    BattleMapStage battleMapStage;
    BattleMapActorStage actorStage;
    BattleMapGUIStage guiStage;
    public BattleScreen(Client game) {
        super(game);
    }

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
    public void setBattleMap(WorldMap worldMap){
        battleMapStage.setBattleMap(worldMap);
    }
}
