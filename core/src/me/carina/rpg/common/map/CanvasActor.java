package me.carina.rpg.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class CanvasActor extends Actor {
    Array<Picture> pictures = new Array<>();
    public void addPicture(Picture picture){
        pictures.add(picture);
    }
    public void addPicture(Drawable drawable, float x, float y, float width, float height){
        pictures.add(new Picture(drawable,x,y,width,height));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Picture picture : pictures) {
            Vector2 v = new Vector2(picture.x,picture.y);
            screenToLocalCoordinates(v);
            Vector2 v1 = new Vector2(picture.x+picture.width, picture.y+picture.height);
            screenToLocalCoordinates(v1);
            picture.drawable.draw(batch,v.x, v.y, v1.x-v.x, v1.y-v.y);
        }
        pictures.clear();
    }

    public static class Picture{
        //SCREEN COORDINATES AND SIZE
        Drawable drawable;
        float x;
        float y;
        float width;
        float height;
        public Picture(Drawable drawable, float x, float y, float width, float height){
            this.drawable = drawable;
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }

    }
}
