package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import me.carina.rpg.client.misc.CameraUtil;

/**
 * Moves camera while maintaining its rotation
 */
public class CameraMoveAction extends AbstractCameraControlAction{
    Vector2 targetVec;
    Vector2 beginVec;
    public CameraMoveAction(Camera camera) {
        super(camera);
    }

    public void setTargetPos(Vector2 target) {
        this.targetVec = target.cpy();
    }

    public void setTargetPos(float x, float y) {
        this.targetVec = new Vector2(x,y);
    }

    @Override
    protected void begin() {
        beginVec = CameraUtil.getFocus(camera);
    }

    @Override
    protected void update(float percent) {
        CameraUtil.move(camera,targetVec.cpy().sub(beginVec).scl(percent).add(beginVec));
    }

}
