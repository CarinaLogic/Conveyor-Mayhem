package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.GameObject;

public abstract class GameStage extends Stage implements GameObject {
    Client game;

    public GameStage(Viewport viewport){
        super(viewport);
    }
    public GameStage(Viewport viewport, Batch batch){
        super(viewport, batch);
    }
    //The game instance is already set
    public abstract void init();

    @Override
    public Client getGame() {
        return game;
    }

    @Override
    public GameStage setGame(AbstractGameInstance game) {
        if (game instanceof Client){
            this.game = (Client) game;
        }
        else game.getLogger().error("Non-client instance tried to bind to Stage, ignoring...");
        return this;
    }
}
