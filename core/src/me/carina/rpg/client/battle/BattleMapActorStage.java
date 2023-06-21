package me.carina.rpg.client.battle;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.carina.rpg.client.scenes.GameStage;
import me.carina.rpg.common.world.map.CanvasActor;

public class BattleMapActorStage extends GameStage {
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
