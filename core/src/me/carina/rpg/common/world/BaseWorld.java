package me.carina.rpg.common.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public abstract class BaseWorld{
    AbstractGameInstance game;
    World world;
    Array<Body> bodies = new Array<>();
    public BaseWorld() {
        world = new World(new Vector2(0, -10), true);
        world.setContactFilter((fixtureA, fixtureB) -> {
                    Object dataA = fixtureA.getBody().getUserData();
                    Object dataB = fixtureB.getBody().getUserData();
                    if (dataA instanceof WorldComponentDef componentA && dataB instanceof WorldComponentDef componentB) {
                        return !componentA.collisionType.collideWith(componentB.collisionType);
                    }
                    return false;
                }
        );
    }

    public Body addComponent(WorldComponentDef component, float x, float y){
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
        body.setUserData(component.data);
        polygonShape.dispose();
        return body;
    }

    public void removeComponent(Body body){
        world.destroyBody(body);
    }

    public Array<Body> getBodies() {
        return bodies;
    }

    public abstract void update(float step);

    public void setGame(AbstractGameInstance game) {
        this.game = game;
    }

    public AbstractGameInstance getGame() {
        return game;
    }

    public void refreshBodies(){
        world.getBodies(bodies);
    }

    public World getBox2dWorld() {
        return world;
    }
}
