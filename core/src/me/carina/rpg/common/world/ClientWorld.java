package me.carina.rpg.common.world;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.world.BaseWorld;

public class ClientWorld extends BaseWorld{
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    @Override
    public void draw(Batch batch, float parentAlpha){
        bodies.forEach(body -> {
            Object o = body.getUserData();
            if (o instanceof WorldComponent component){
                Vector2 pos = body.getPosition().add(-component.width/2,-component.height/2);
                batch.draw(component.texture,pos.x,pos.y,component.width,component.height);
            }
        });
        //debugRenderer.render(world, getStage().getCamera().combined);
    }

}
