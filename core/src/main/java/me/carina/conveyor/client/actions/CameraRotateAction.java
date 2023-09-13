package me.carina.conveyor.client.actions;

import com.badlogic.gdx.graphics.Camera;
import me.carina.conveyor.client.misc.CameraUtil;

/**
 * Moves and rotates camera while maintaining focus and z position
 */
public class CameraRotateAction extends AbstractCameraControlAction{
    float targetRotation;
    float beginRotation;
    float z = 0;
    public CameraRotateAction(Camera camera) {
        super(camera);
    }

    public void setZ(float z){
        this.z = z;
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
        CameraUtil.rotate(camera,(targetRotation-beginRotation)*percent+beginRotation,z);
    }

}
