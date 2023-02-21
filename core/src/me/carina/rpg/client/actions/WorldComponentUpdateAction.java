package me.carina.rpg.client.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.common.world.WorldComponentActor;

public class WorldComponentUpdateAction extends Action {

    @Override
    public boolean act(float delta) {
        WorldComponentActor componentActor = (WorldComponentActor) getTarget();
        Body body = componentActor.getBody();
        Vector2 pos = body.getPosition();
        componentActor.setPosition(pos.x - componentActor.getWidth()/2, pos.y - componentActor.getHeight()/2);
        componentActor.setOrigin(Align.center);
        float angle = body.getAngle();
        componentActor.setRotation(MathUtils.radiansToDegrees * angle);
        return true;
    }
}
