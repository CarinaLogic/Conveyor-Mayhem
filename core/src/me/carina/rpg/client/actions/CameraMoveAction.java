package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import me.carina.rpg.client.misc.CameraUtil;

/**
 * Moves camera while maintaining its rotation and z position
 */
public class CameraMoveAction extends AbstractCameraControlAction{
    Vector3 targetVec;
    Vector3 beginVec;
    public CameraMoveAction(Camera camera) {
        super(camera);
    }

    public void setTargetPos(Vector3 target) {
        this.targetVec = target.cpy();
    }

    public void setTargetPos(float x, float y, float z) {
        this.targetVec = new Vector3(x,y,z);
    }

    @Override
    protected void begin() {
        beginVec = CameraUtil.getFocus(camera,targetVec.z);
    }

    @Override
    protected void update(float percent) {
        CameraUtil.move(camera,targetVec.cpy().sub(beginVec).scl(percent).add(beginVec));
    }

}
