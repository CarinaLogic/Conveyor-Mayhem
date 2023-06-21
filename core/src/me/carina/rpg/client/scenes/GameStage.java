package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;

public abstract class GameStage extends Stage{
    public GameStage(Viewport viewport){
        super(viewport);
    }
    public GameStage(Viewport viewport, Batch batch){
        super(viewport, batch);
    }
    public abstract void init();
}
