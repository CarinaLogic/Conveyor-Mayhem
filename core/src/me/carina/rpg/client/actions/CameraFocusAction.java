package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.client.misc.CameraUtil;

/**
 * Moves camera while maintaining its rotation
 */
public class CameraFocusAction extends AbstractCameraControlAction{
    Vector2 targetVec;
    Vector2 beginVec;
    public CameraFocusAction(Camera camera) {
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
        camera.position.set(camera.position.set(CameraUtil.getIdealCameraPos(camera,targetVec.sub(beginVec).scl(percent))));
    }

}
