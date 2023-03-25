package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import me.carina.rpg.common.map.CanvasActor;

public class BattleMapActorStage extends GameStage{
    CanvasActor canvas;
    public BattleMapActorStage(){
        super(new ExtendViewport(10,10));
    }
    @Override
    public void init() {
        canvas = new CanvasActor();
        addActor(canvas);
    }
}
