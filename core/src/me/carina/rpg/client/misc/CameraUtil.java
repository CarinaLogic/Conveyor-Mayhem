package me.carina.rpg.client.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraUtil {
    static final float baseZoomDistance = 6;
    static final float baseZoomRotation = (float) (Math.PI/4);
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
    public static float getZoom(Camera camera){
        Vector3 f = new Vector3(getFocus(camera),0);
        Vector3 pos = camera.position.cpy();
        float d = f.sub(pos).len();
        return baseZoomDistance / d;
    }
    public static void zoom(Camera camera, float zoom){
        //WTF
        if (zoom < 0.5f) throw new IllegalArgumentException("Zoom amount must be equal or more than 0.5");
        Vector3 f = new Vector3(getFocus(camera),0);
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        float l = pos.cpy().sub(f).len();
        float theta = (float) Math.asin(pos.z/l);
        float tL = baseZoomDistance / zoom;
        float tTheta = baseZoomRotation / zoom;
        float l1 = (float) Math.cos(theta) * l;
        float tl1 = (float) Math.cos(tTheta) * tL;
        Vector3 od = dir.cpy().scl(-l);
        float x = od.x * tl1 / l1;
        float y = od.y * tl1 / l1;
        float z = (float) Math.sin(tTheta) * tL;
        camera.position.set(x,y,z).add(f);
        camera.direction.set(-x,-y,-z).nor();
        camera.up.set(-x,-y,tL).nor();
        camera.update();
    }
}
