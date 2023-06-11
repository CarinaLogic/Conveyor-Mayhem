package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import me.carina.rpg.common.world.map.WorldMap;
import me.carina.rpg.common.world.map.WorldMapActor;
import me.carina.rpg.common.world.map.CanvasActor;

public class BattleMapStage extends GameStage {
    WorldMapActor worldMapActor;
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

    public void setBattleMap(WorldMap worldMap) {
        this.worldMapActor = new WorldMapActor(worldMap);
        addActor(worldMapActor);
        setScrollFocus(worldMapActor);
    }

    public CanvasActor getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasActor canvas) {
        this.canvas = canvas;
    }
}
