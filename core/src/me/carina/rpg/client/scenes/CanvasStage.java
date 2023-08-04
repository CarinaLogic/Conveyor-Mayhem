package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.carina.rpg.client.scenes.GameStage;
import me.carina.rpg.common.world.map.CanvasActor;

public class CanvasStage extends GameStage {
    public CanvasStage(){
        super(new ScreenViewport());
    }
    //The operation is HEAVY, use accordingly
    public void draw(Drawable drawable, float x, float y, float width, float height, Color color, float parentAlpha) {
        Camera camera = getViewport().getCamera();
        camera.update();
        Batch batch = getBatch();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawable.draw(batch,x,y,width,height);
        batch.end();
    }
    public void draw(TransformDrawable drawable, float x, float y, float originX, float originY, float width,
                     float height,float scaleX, float scaleY, float rotation, Color color, float parentAlpha){
        Camera camera = getViewport().getCamera();
        camera.update();
        Batch batch = getBatch();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawable.draw(batch,x,y,originX,originY,width,height,scaleX,scaleY,rotation);
        batch.end();
    }

    @Override
    public void init() {

    }
}
