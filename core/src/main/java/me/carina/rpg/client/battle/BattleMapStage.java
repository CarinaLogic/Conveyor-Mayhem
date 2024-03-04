package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.Game;
import me.carina.rpg.client.actions.CameraTargetAction;
import me.carina.rpg.client.scenes.GameStage;
import me.carina.rpg.client.scenes.PerspectiveViewport;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.battle.BattleMapDisplay;

import java.util.function.Supplier;

public class BattleMapStage extends GameStage<BattleScreen> {
    static Supplier<BattleMap> invBattleMap = () -> Game.getClient().getContext().get(BattleMap.class);
    public BattleMapStage(){
        super(new PerspectiveViewport(10,10,new PerspectiveCamera(70,10,10)));
    }

    @Override
    public void init() {
        addActor(Game.getClient().getDisplays().get(invBattleMap,BattleMapDisplay.class));
        addAction(new CameraTargetAction(getCamera(), new Vector3(2,2,0), 1,1f,2));
        getCamera().near = 0.1f;
        getCamera().far = 100;
        getCamera().position.set(5,3,5);
        getCamera().lookAt(5,5,0);
    }

}
