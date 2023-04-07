package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import me.carina.rpg.common.map.BattleMap;
import me.carina.rpg.common.map.BattleMapActor;
import me.carina.rpg.common.map.CanvasActor;

public class BattleMapStage extends GameStage {
    BattleMapActor battleMapActor;
    CanvasActor canvas;
    public BattleMapStage(){
        super(new PerspectiveViewport(10,10,new PerspectiveCamera(70,10,10)));
    }

    @Override
    public void init() {
        getCamera().near = 0.1f;
        getCamera().far = 100;
        getCamera().position.set(5,3,5);
        getCamera().lookAt(5,5,0);
        setDebugAll(true);
    }

    public void setBattleMap(BattleMap battleMap) {
        this.battleMapActor = new BattleMapActor(battleMap);
        addActor(battleMapActor);
        setScrollFocus(battleMapActor);
    }

    public CanvasActor getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasActor canvas) {
        this.canvas = canvas;
    }
}
