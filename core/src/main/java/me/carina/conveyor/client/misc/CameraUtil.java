package me.carina.conveyor.client.misc;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraUtil {
    static final float baseZoomDistance = 6;
    static final float baseZoomRotation = (float) (Math.PI/4);
    //I hate linear algebra so step-by-step it is
    public static Vector3 getFocus(Camera camera){
        return getFocus(camera,0);
    }
    public static Vector3 getFocus(Camera camera, float z){
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        float mul = (pos.z - z) / dir.z;
        return pos.sub(dir.scl(mul));
    }
    public static Vector3 getIdealCameraPos(Camera camera, Vector3 focus){
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        float mul = (pos.z - focus.z) / dir.z;
        dir.scl(mul);
        return focus.cpy().add(dir);
    }
    public static void move(Camera camera, Vector3 focus){
        camera.position.set(getIdealCameraPos(camera, focus));
        camera.update();
    }
    public static float getRotation(Camera camera){
        return (float) Math.atan2(camera.direction.y,camera.direction.x);
    }
    public static void rotate(Camera camera, float rotation, float z){
        Vector3 f = getFocus(camera,z);
        Vector3 dir = camera.direction.cpy();
        float l = new Vector2(dir.x,dir.y).len();
        float px = (float) Math.cos(rotation) * l;
        float py = (float) Math.sin(rotation) * l;
        float pz = dir.z;
        camera.direction.set(px,py,pz).nor();
        camera.up.set(px,py,l).nor();
        camera.update();
        camera.position.set(getIdealCameraPos(camera,f));
        camera.update();
    }
    public static float getZoom(Camera camera, float z){
        Vector3 f = getFocus(camera,z);
        Vector3 pos = camera.position.cpy();
        float d = f.sub(pos).len();
        return baseZoomDistance / d;
    }
    public static void zoom(Camera camera, float zoom, float z){
        //WTF
        if (zoom < 0.5f) throw new IllegalArgumentException("Zoom amount must be equal or more than 0.5");
        Vector3 f = getFocus(camera,z);
        Vector3 pos = camera.position.cpy();
        Vector3 dir = camera.direction.cpy();
        float l = pos.cpy().sub(f).len();
        float theta = (float) Math.asin((pos.z - f.z)/l);
        float tL = baseZoomDistance / zoom;
        float tTheta = baseZoomRotation / zoom;
        float l1 = (float) Math.cos(theta) * l;
        float tl1 = (float) Math.cos(tTheta) * tL;
        Vector3 od = dir.cpy().scl(-l);
        float px = od.x * tl1 / l1;
        float py = od.y * tl1 / l1;
        float pz = (float) Math.sin(tTheta) * tL;
        camera.position.set(px,py,pz).add(f);
        camera.direction.set(-px,-py,-pz).nor();
        camera.up.set(-px,-py,tL).nor();
        camera.update();
    }
}
