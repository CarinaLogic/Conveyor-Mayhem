package me.carina.conveyor.client.scenes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class PerspectiveViewport extends ExtendViewport {
    final Vector3 tmp = new Vector3();

    public PerspectiveViewport(float minWorldWidth, float minWorldHeight) {
        super(minWorldWidth, minWorldHeight);
    }

    public PerspectiveViewport(float minWorldWidth, float minWorldHeight, Camera camera) {
        super(minWorldWidth, minWorldHeight, camera);
    }

    public PerspectiveViewport(float minWorldWidth, float minWorldHeight, float maxWorldWidth, float maxWorldHeight) {
        super(minWorldWidth, minWorldHeight, maxWorldWidth, maxWorldHeight);
    }

    public PerspectiveViewport(float minWorldWidth, float minWorldHeight, float maxWorldWidth, float maxWorldHeight, Camera camera) {
        super(minWorldWidth, minWorldHeight, maxWorldWidth, maxWorldHeight, camera);
    }

    @Override
    public Vector2 project(Vector2 worldCoords) {
        tmp.set(worldCoords,0);
        super.project(tmp);
        return worldCoords.set(tmp.x,tmp.y);
    }

    @Override
    public Vector2 unproject(Vector2 screenCoords) {
        tmp.set(screenCoords,1);
        super.unproject(tmp);
        tmp.sub(getCamera().position);
        tmp.scl(getCamera().position.z/tmp.z);
        tmp.set(getCamera().position.cpy().sub(tmp));
        return screenCoords.set(tmp.x,tmp.y);
    }

    @Override
    public void apply(boolean centerCamera) {
        HdpiUtils.glViewport(getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        getCamera().viewportWidth = getWorldWidth();
        getCamera().viewportHeight = getWorldHeight();
        getCamera().update();
    }
}
