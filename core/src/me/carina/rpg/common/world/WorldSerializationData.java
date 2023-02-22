package me.carina.rpg.common.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import me.carina.rpg.client.Client;

public class WorldSerializationData {
    Array<WorldComponentSerializationData> components;
    public WorldSerializationData(){} //for json loading
    public WorldSerializationData(BaseWorld world){
        components = new Array<>();
        world.refreshBodies();
        for (Body body : world.getBodies()) {
            Vector2 pos = body.getPosition();
            components.add(new WorldComponentSerializationData(pos.x, pos.y,
                    body.getAngle(), (WorldComponentData) body.getUserData()));
        }
    }
    public BaseWorld toWorld(AbstractGameInstance game){
        BaseWorld world = game.newWorld();
        for (WorldComponentSerializationData component : components) {
            WorldComponentDef def = WorldComponentDef.fromId(component.data.id,game);
            if (def != null) {
                world.addComponent(def, component.x, component.y);
            }
            else game.logger.error("Could not fetch definition of received WorldComponent, ignoring...");
        }
        return world;
    }
}
