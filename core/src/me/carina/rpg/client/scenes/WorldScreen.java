package me.carina.rpg.client.scenes;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.world.BlockDef;
import me.carina.rpg.common.world.ClientWorld;
import me.carina.rpg.common.world.WorldActor;

public class WorldScreen extends BaseScreen{
    Stage worldStage;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public WorldScreen(Client client){
        super(client);
    }

    @Override
    public void show() {
        worldStage = addStage(new GUIStage());
        worldStage.addActor(game.getWorld().getWorldActor());
        game.getWorld().addComponent(BlockDef.fromId("testBlock",game),0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        debugRenderer.render(game.getWorld().getBox2dWorld(),worldStage.getCamera().combined);
    }
}
