package me.carina.rpg.common.world;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.client.actions.WorldUpdateAction;

public class WorldActor extends Group {
    ClientWorld world;
    public WorldActor(ClientWorld world){
        this.world = world;
        addAction(new WorldUpdateAction());
    }
    public ClientWorld getWorld() {
        return world;
    }
}
