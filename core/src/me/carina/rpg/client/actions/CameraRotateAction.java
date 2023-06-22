package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import me.carina.rpg.client.misc.CameraUtil;

import java.util.Optional;

/**
 * Moves and rotates camera while maintaining focus and z position
 */
public class CameraRotateAction extends AbstractCameraControlAction{
    float targetRotation;
    float beginRotation;
    public CameraRotateAction(Camera camera) {
        super(camera);
    }

    @Override
    protected void begin() {
        beginRotation = CameraUtil.getRotation(camera);
    }

    public void setTargetRotation(float rot){
        this.targetRotation = rot;
    }

    @Override
    protected void update(float percent) {
        CameraUtil.rotate(camera,(targetRotation-beginRotation)*percent+beginRotation);
    }

}
