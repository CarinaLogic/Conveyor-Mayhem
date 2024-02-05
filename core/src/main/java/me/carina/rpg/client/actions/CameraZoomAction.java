package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import me.carina.rpg.client.misc.CameraUtil;

/**
 * Moves and rotates camera while maintaining xy position and xy rotation
 */
public class CameraZoomAction extends AbstractCameraControlAction{
    float targetZoom;
    float beginZoom;
    float z = 0;
    public CameraZoomAction(Camera camera) {
        super(camera);
    }

    public void setZ(float z){this.z = z;}

    public void setTargetZoom(float zoom){
        this.targetZoom = zoom;
    }

    @Override
    protected void begin() {
        this.beginZoom = CameraUtil.getZoom(camera,z);
    }

    @Override
    protected void update(float percent) {
        CameraUtil.zoom(camera,(targetZoom-beginZoom)*percent+beginZoom,z);
    }
}
