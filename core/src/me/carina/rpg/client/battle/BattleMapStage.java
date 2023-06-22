package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.client.actions.CameraMoveAction;
import me.carina.rpg.client.actions.CameraTargetAction;
import me.carina.rpg.client.scenes.GameStage;
import me.carina.rpg.client.scenes.PerspectiveViewport;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.battle.BattleMapDisplay;
import me.carina.rpg.common.world.map.CanvasActor;

public class BattleMapStage extends GameStage {
    BattleMapDisplay battleMapDisplay;
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
        this.battleMapDisplay = battleMap.newDisplay();
        addActor(battleMapDisplay);
        setScrollFocus(battleMapDisplay);
        battleMapDisplay.addAction(new CameraTargetAction(
                getCamera(), new Vector3(0.5f,1.5f,0.5f), 1,2,2));
    }

    @Override
    public void draw() {
        super.draw();
    }

    public CanvasActor getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasActor canvas) {
        this.canvas = canvas;
    }
}
