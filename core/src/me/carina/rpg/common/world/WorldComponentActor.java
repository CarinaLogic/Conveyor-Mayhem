package me.carina.rpg.common.world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import me.carina.rpg.client.Client;

public class WorldComponentActor extends Image {
    Client game;
    Body body;
    public WorldComponentActor(Body body, float width, float height, Client game){
        this.body = body;
        this.game = game;
        setDrawable(new TextureRegionDrawable(game.getAssets().get(
                ((WorldComponentData)body.getUserData()).id, TextureRegion.class)));
        setSize(width, height);
    }

    @Override
    public void act(float delta) {
        if (!game.getWorld().getBodies().contains(body,true)){
            getParent().removeActor(this);
        }
        else {
            super.act(delta);
        }
    }

    public Client getGame() {
        return game;
    }

    public Body getBody() {
        return body;
    }
}
