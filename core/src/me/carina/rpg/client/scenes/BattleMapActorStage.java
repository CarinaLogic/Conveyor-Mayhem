package me.carina.rpg.client.scenes;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.carina.rpg.common.map.CanvasActor;

public class BattleMapActorStage extends GameStage{
    CanvasActor canvas;
    public BattleMapActorStage(){
        super(new ScreenViewport());
    }
    @Override
    public void init() {
        canvas = new CanvasActor();
        addActor(canvas);
    }
}
