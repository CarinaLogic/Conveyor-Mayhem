package me.carina.rpg.client.scenes;

import me.carina.rpg.client.Client;

public class BattleScreen extends BaseScreen{
    BattleMapStage battleMapStage;
    BattleMapActorStage actorStage;
    public BattleScreen(Client game) {
        super(game);
    }

    @Override
    public void show() {
        battleMapStage = new BattleMapStage();
        addStage(battleMapStage);
        actorStage = new BattleMapActorStage();
        addStage(actorStage);
        battleMapStage.setCanvas(actorStage.canvas);
    }
}
