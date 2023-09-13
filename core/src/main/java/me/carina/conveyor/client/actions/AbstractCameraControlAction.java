package me.carina.conveyor.client.actions;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public abstract class AbstractCameraControlAction extends TemporalAction {
    Camera camera;
    public AbstractCameraControlAction(Camera camera){
        this.camera = camera;
    }
}
