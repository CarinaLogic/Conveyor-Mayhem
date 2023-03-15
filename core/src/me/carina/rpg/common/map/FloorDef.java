package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.server.Server;

public class FloorDef extends AbstractFeatureDef<Floor>{
    String id;
    boolean passable;

    @Override
    public Floor toFeature(AbstractGameInstance game) {
        return new Floor(game,id,passable);
    }

    @Override
    public FloorActor toActor(Client game) {
        return new FloorActor(toFeature(game));
    }
}
