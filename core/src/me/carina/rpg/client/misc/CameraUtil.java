package me.carina.rpg.client.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraUtil {
    //I hate linear algebra so step-by-step it is
    public static Vector2 getFocus(Camera camera){
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        float mul = pos.z / dir.z;
        Vector3 v = pos.sub(dir.scl(mul));
        return new Vector2(v.x,v.y);
    }
    public static Vector3 getIdealCameraPos(Camera camera, Vector2 focus){
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        Vector3 f = new Vector3(focus,0);
        float mul = pos.z / dir.z;
        dir.scl(mul);
        return f.add(dir);
    }
    public static void move(Camera camera, Vector2 focus){
        camera.position.set(getIdealCameraPos(camera, focus));
        camera.update();
    }
    public static float getRotation(Camera camera){
        return (float) Math.atan2(camera.direction.y,camera.direction.x);
    }
    public static void rotate(Camera camera, float rotation){
        Vector2 f = getFocus(camera);
        Vector3 dir = camera.direction.cpy();
        float l = new Vector2(dir.x,dir.y).len();
        float x = (float) Math.cos(rotation) * l;
        float y = (float) Math.sin(rotation) * l;
        float z = dir.z;
        camera.direction.set(x,y,z).nor();
        camera.up.set(x,y,l).nor();
        camera.update();
        camera.position.set(getIdealCameraPos(camera,f));
        camera.update();
    }
}
