package me.carina.rpg.client.actions;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class CameraTargetAction extends ParallelAction {
    Camera camera;
    CameraMoveAction moveAction;
    CameraRotateAction rotateAction;
    CameraZoomAction zoomAction;
    public CameraTargetAction(Camera camera, Vector2 targetPos, float targetRotation, float targetZoom, float duration) {
        this.camera = camera;
//        moveAction = new CameraMoveAction(camera);
//        moveAction.setTargetPos(targetPos);
//        moveAction.setDuration(duration);
//        addAction(moveAction);
//        rotateAction = new CameraRotateAction(camera);
//        rotateAction.setTargetRotation(targetRotation);
//        rotateAction.setDuration(duration);
//        addAction(rotateAction);
        zoomAction = new CameraZoomAction(camera);
        zoomAction.setTargetZoom(targetZoom);
        zoomAction.setDuration(duration);
        addAction(zoomAction);
    }
}
