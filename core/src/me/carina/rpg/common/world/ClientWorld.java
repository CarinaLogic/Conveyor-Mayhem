package me.carina.rpg.common.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import me.carina.rpg.client.Client;
import me.carina.rpg.server.Server;

public class ClientWorld extends BaseWorld{
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    WorldActor worldActor;

    public ClientWorld(Client game){
        super(game);
        worldActor = new WorldActor(this);
    }

    @Override
    public Body addComponent(WorldComponentDef component, float x, float y) {
        Body body = super.addComponent(component, x, y);
        worldActor.addActor(new WorldComponentActor(body,component.width,component.height,getGame()));
        return body;
    }

    @Override
    public void removeComponent(Body body) {
        super.removeComponent(body);
    }

    public WorldActor getWorldActor() {
        return worldActor;
    }

    @Override
    public void update(float step){
        world.getBodies(bodies);
        world.step(step,6,2);
    }



    @Override
    public Client getGame() {
        return (Client) super.getGame();
    }
}
