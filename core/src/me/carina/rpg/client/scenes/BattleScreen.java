package me.carina.rpg.client.scenes;

import me.carina.rpg.client.Client;

public class BattleScreen extends BaseScreen{
    public BattleScreen(Client game) {
        super(game);
    }

    @Override
    public void show() {
        addStage(new BattleMapStage());
    }
}
