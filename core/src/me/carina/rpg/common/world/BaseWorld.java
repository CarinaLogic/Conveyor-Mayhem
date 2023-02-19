package me.carina.rpg.common.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class BaseWorld extends Actor {
    AbstractGameInstance game;
    World world;
    Array<Body> bodies = new Array<>();
    float accumulatedTime = 0f;
    final float step = 1/60f;
    public BaseWorld() {
        world = new World(new Vector2(0, -10), true);
        world.setContactFilter((fixtureA, fixtureB) -> {
                    Object dataA = fixtureA.getBody().getUserData();
                    Object dataB = fixtureB.getBody().getUserData();
                    if (dataA instanceof WorldComponent componentA && dataB instanceof WorldComponent componentB) {
                        return !componentA.collisionType.collideWith(componentB.collisionType);
                    }
                    return false;
                }
        );
    }

    public void addComponent(WorldComponent component, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x+component.width/2, y+component.height/2);
        bodyDef.type = component.bodyType;
        Body body = world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(component.width/2,component.height/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = component.density;
        fixtureDef.friction = component.friction;
        fixtureDef.restitution = component.restitution;
        body.createFixture(fixtureDef);
        body.setUserData(component);
        if (this.game != null) component.game = this.game;
        polygonShape.dispose();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        world.getBodies(bodies);
        float frameTime = Math.min(delta, 0.25f);
        accumulatedTime += frameTime;
        while (accumulatedTime >= step) {
            world.step(step, 6, 2);
            accumulatedTime -= step;
        }
    }

    public void setGame(AbstractGameInstance game) {
        this.game = game;
    }
}
