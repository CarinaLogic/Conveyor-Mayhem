package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.client.actions.CameraTargetAction;
import me.carina.rpg.client.scenes.GameStage;
import me.carina.rpg.client.scenes.PerspectiveViewport;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.battle.BattleMapDisplay;

public class BattleMapStage extends GameStage {
    BattleMapDisplay battleMapDisplay;
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

    public BattleMap getBattleMap(){
        return ((BattleMap) battleMapDisplay.getFeature());
    }

    public void setBattleMap(BattleMap battleMap) {
        this.battleMapDisplay = (BattleMapDisplay) battleMap.generateDisplay();
        addActor(battleMapDisplay);
        battleMapDisplay.addAction(new CameraTargetAction(getCamera(), new Vector3(2,2,0), 1,1f,2));
    }

    @Override
    public void draw() {
        super.draw();
    }

}
