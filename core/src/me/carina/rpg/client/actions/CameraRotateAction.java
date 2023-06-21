package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;

import java.util.Optional;

/**
 * Rotates camera while maintaining focus
 */
public class CameraRotateAction extends AbstractCameraControlAction{
    float targetRotation;
    float beginRotation;
    public CameraRotateAction(Camera camera) {
        super(camera);
    }

    @Override
    protected void begin() {

    }

    @Override
    protected void update(float percent) {

    }
}
