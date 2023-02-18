package me.carina.rpg.client.scenes;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.world.Block;
import me.carina.rpg.common.world.ClientWorld;

public class WorldScreen extends BaseScreen{
    Client client;
    Stage worldStage;
    public WorldScreen(Client client){
        super(client);
        this.client = client;
    }

    @Override
    public void show() {
        worldStage = addStage(new GUIStage());
        worldStage.addActor(client.getWorld());
        client.getWorld().addComponent(client.getAssets().get("block.testBlock", Block.class),0,0);
    }
}
